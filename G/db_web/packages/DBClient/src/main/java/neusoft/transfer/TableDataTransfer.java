package neusoft.transfer;

import com.neusoft.ui.bean.TableDataListDtoInner;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class TableDataTransfer {
    public TableDataTransfer(Object[] sqlObjects) {
        int index = 0;
        this.tableColumn = (String) sqlObjects[index++];
        this.tableRow = (String) sqlObjects[index++];
        this.lineNumber = (Integer) sqlObjects[index++];
    }

    public TableDataTransfer(String tableColumn, Object tableRow, Integer lineNumber) {
        this.tableColumn = tableColumn;
        this.tableRow = tableRow;
        this.lineNumber = lineNumber;
    }

    private String tableColumn;
    private Object tableRow;
    private Integer lineNumber;
    private Object tmp;

    public TableDataListDtoInner toDto() {
        TableDataListDtoInner tableDataListDtoInner = new TableDataListDtoInner();
        if ("column_name".equals(tableColumn)) {
            tableDataListDtoInner.setTableColumn((String) tableRow);
            tableDataListDtoInner.setTableRow("");
            tableDataListDtoInner.setLineNumber(0);
            return tableDataListDtoInner;
        }
        tableDataListDtoInner.setTableColumn(tableColumn);
        tableDataListDtoInner.setTableRow(tableRow);
        tableDataListDtoInner.setLineNumber(lineNumber);
        return tableDataListDtoInner;
    }
}
