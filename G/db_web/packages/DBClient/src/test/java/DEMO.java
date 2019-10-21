import neusoft.Application;
import neusoft.Exception.ExceptionClass.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static neusoft.Exception.ErrorMessage.SQLError;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class DEMO {
    private static final Pattern MSISDN_PATTERN = Pattern.compile("^0\\d{10}$");
    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional()
    public void optionData() {
        Query nativeQuery = entityManager.createNativeQuery("DELETE FROM `db_web`.`new_table1` WHERE id='1'");
        nativeQuery
                .executeUpdate();
        entityManager.close();
    }
}