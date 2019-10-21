package neusoft.service;

import com.neusoft.domain.UrlAddress;
import com.neusoft.repository.UrlRepository;
import lombok.extern.log4j.Log4j2;
import neusoft.Exception.ExceptionClass.LoginException;
import neusoft.Exception.ExceptionClass.SQLException;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.*;

import static neusoft.Exception.ErrorMessage.*;


@Service
@Log4j2
public class JobService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    HttpServletRequest request;

    HttpSession session;

    /**
     * login DB System
     *
     * @param userName
     * @param password
     * @param urlName
     */
    public void login(String userName, String password, String urlName) {
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "Try to log in");
        Connection connection = null;
        if (!StringUtils.isEmpty(userName)
                && !StringUtils.isEmpty(password)
                && !StringUtils.isEmpty(urlName)) {
            urlName = "jdbc:mysql://" + urlName + ":3306/mysql";
            checkURLAndUserNamePassword(userName, password, urlName);
            EntityManager entityManager = entityManager(userName, password, urlName);
            getSession();
            session.setAttribute("entityManager", entityManager);
            return;
        }
        throw new LoginException(loginError, loginError);
    }

    private void checkURLAndUserNamePassword(String userName, String password, String urlName) {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(urlName, userName, password);
            connection.close();
        } catch (Exception e) {
            throw new LoginException(loginError, loginError);
        }
    }

    private void getSession() {
        session = request.getSession();
    }

    /**
     * logout DB System
     */
    public void logout() {
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "log out system");
        Enumeration<String> enumeration = request.getSession().getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            session.removeAttribute(key);
        }
    }

    /**
     * find all table of  current dataBase
     *
     * @param dbName
     * @return
     */
    public List<String> searchDBTable(String dbName) {
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "search db table");
        TrafferCurrentDB(dbName);
        String sql_showDbTable = "show tables";
        List list = queryList(sql_showDbTable);
        session.setAttribute("list", list);
        session.setAttribute("dbName", dbName);
        return list;
    }

    /**
     * find  table  date of  current dataBase
     *
     * @param condition
     * @return
     */
    public List<Map<String, Object>> searchDBTableDate(String condition) {
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "search db table data");
//        TrafferCurrentDB();
        List<Map<String, Object>> list = queryMap(condition);
        if (StringUtils.isEmpty(list)) {
            throw new SQLException(SQLError, SQLError);
        }
        if (list.size() == 0) {
            List<String> tableNameList = (List<String>) session.getAttribute("list");
            String DBName = (String) session.getAttribute("dbName");
            tableNameList.forEach(
                    tableName -> {
                        if (condition.contains(tableName)) {
                            queryMap(
                                    "SELECT column_name FROM information_schema.columns WHERE table_name='" + dbNameTrim(tableName) + "' And table_schema='" + dbNameTrim(DBName) + "'").forEach(
                                    stringObjectMap -> {
                                        if (!list.contains(stringObjectMap)) {
                                            list.add(stringObjectMap);
                                        }
                                    }
                            );
                        }
                    }
            );

        }
        return list;
    }


    /**
     * find all url address
     *
     * @return
     */
    public List<UrlAddress> searchDBUrl() {
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "find all url address");
        List<UrlAddress> all = urlRepository.findAll();
        return all;
    }

    /**
     * find all db name
     *
     * @return
     */
    public List<String> searchDBName() {
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "find all db name");
        return queryList("show databases");
    }

    /**
     * option data
     *
     * @param condition
     */
    @Transactional
    public void optionData(String condition) {
        try {
            log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "option data");
            EntityManager entityManager = (EntityManager) session.getAttribute("entityManager");
            entityManager.createNativeQuery(condition).executeUpdate();
        } catch (Exception e) {
            throw new SQLException(SQLError, SQLError);
        }
    }


    private String dbNameTrim(String tableName) {
        return tableName.trim();
    }


    private List queryList(String sql) {
        EntityManager entityManager = (EntityManager) session.getAttribute("entityManager");
        Query query = entityManager.createNativeQuery(sql);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    private List<Map<String, Object>> queryMap(String sql) {
        EntityManager entityManager = (EntityManager) session.getAttribute("entityManager");
        Query query = entityManager.createNativeQuery(sql);
        try {
            query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    private void TrafferCurrentDB(String dbName) {
        if (StringUtils.isEmpty(dbName)) {
            throw new LoginException(Timeout, Timeout);
        }
        String sql_TranferDB = "use " + dbName;
        queryList(sql_TranferDB);
    }

    public EntityManager entityManager(String userName, String password, String url) {
        DataSource dataSource =
                DataSourceBuilder
                 .create()
                 .username(userName)
                 .driverClassName("com.mysql.jdbc.Driver")
                 .password(password)
                 .url(url)
                 .build();

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.neusoft.domain");
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("name");
        localContainerEntityManagerFactoryBean.afterPropertiesSet();
        EntityManagerFactory entityManagerFactory = localContainerEntityManagerFactoryBean.getObject();
        return entityManagerFactory.createEntityManager(SynchronizationType.SYNCHRONIZED);
    }

}
