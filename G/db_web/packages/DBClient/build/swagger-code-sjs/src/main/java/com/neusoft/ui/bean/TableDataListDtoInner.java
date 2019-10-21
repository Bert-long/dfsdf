package com.neusoft.ui.bean;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TableDataListDtoInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-16T11:49:18.399439400+08:00[Asia/Shanghai]")
public class TableDataListDtoInner   {
  @JsonProperty("tableColumn")
  private String tableColumn = null;

  @JsonProperty("tableRow")
  private Object tableRow = null;

  @JsonProperty("lineNumber")
  private Integer lineNumber = null;

  public TableDataListDtoInner tableColumn(String tableColumn) {
    this.tableColumn = tableColumn;
    return this;
  }

  /**
   * column name
   * @return tableColumn
  **/
  @ApiModelProperty(value = "column name")

  public String getTableColumn() {
    return tableColumn;
  }

  public void setTableColumn(String tableColumn) {
    this.tableColumn = tableColumn;
  }

  public TableDataListDtoInner tableRow(Object tableRow) {
    this.tableRow = tableRow;
    return this;
  }

  /**
   * row value
   * @return tableRow
  **/
  @ApiModelProperty(value = "row value")

  public Object getTableRow() {
    return tableRow;
  }

  public void setTableRow(Object tableRow) {
    this.tableRow = tableRow;
  }

  public TableDataListDtoInner lineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
    return this;
  }

  /**
   * Number of rows
   * @return lineNumber
  **/
  @ApiModelProperty(value = "Number of rows")

  public Integer getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TableDataListDtoInner tableDataListDtoInner = (TableDataListDtoInner) o;
    return Objects.equals(this.tableColumn, tableDataListDtoInner.tableColumn) &&
        Objects.equals(this.tableRow, tableDataListDtoInner.tableRow) &&
        Objects.equals(this.lineNumber, tableDataListDtoInner.lineNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tableColumn, tableRow, lineNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TableDataListDtoInner {\n");
    
    sb.append("    tableColumn: ").append(toIndentedString(tableColumn)).append("\n");
    sb.append("    tableRow: ").append(toIndentedString(tableRow)).append("\n");
    sb.append("    lineNumber: ").append(toIndentedString(lineNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
