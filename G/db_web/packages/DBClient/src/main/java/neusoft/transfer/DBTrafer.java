package neusoft.transfer;

import com.neusoft.ui.bean.DBListDtoInner;
import com.neusoft.ui.bean.TableListDtoInner;

public class DBTrafer {
    public DBTrafer(Object[] sqlObjects) {
        int index = 0;
        this.daName = (String) sqlObjects[index++];
    }

    public DBTrafer(String daName) {
        this.daName = daName;
    }

    private String daName;

    public DBListDtoInner toDto() {
        DBListDtoInner dbListDtoInner = new DBListDtoInner();
        dbListDtoInner.setDatabaseName(daName);
        return dbListDtoInner;
    }
}
