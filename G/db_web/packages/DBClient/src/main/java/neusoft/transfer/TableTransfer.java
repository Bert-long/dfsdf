package neusoft.transfer;

import com.neusoft.ui.bean.TableListDtoInner;
import lombok.Data;

@Data
public class TableTransfer {
    public TableTransfer(Object[] sqlObjects) {
        int index = 0;
        this.tableDate = (String) sqlObjects[index++];
    }

    public TableTransfer(String tableDate) {
        this.tableDate = tableDate;
    }

    private String tableDate;

    public TableListDtoInner toDto() {
        TableListDtoInner tableListDtoInner = new TableListDtoInner();
        tableListDtoInner.setTableDate(tableDate);
        return tableListDtoInner;
    }
}
