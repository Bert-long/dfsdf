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
 * TableListDtoInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-16T11:49:18.399439400+08:00[Asia/Shanghai]")
public class TableListDtoInner   {
  @JsonProperty("tableDate")
  private String tableDate = null;

  public TableListDtoInner tableDate(String tableDate) {
    this.tableDate = tableDate;
    return this;
  }

  /**
   * table name
   * @return tableDate
  **/
  @ApiModelProperty(value = "table name")

  public String getTableDate() {
    return tableDate;
  }

  public void setTableDate(String tableDate) {
    this.tableDate = tableDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TableListDtoInner tableListDtoInner = (TableListDtoInner) o;
    return Objects.equals(this.tableDate, tableListDtoInner.tableDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tableDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TableListDtoInner {\n");
    
    sb.append("    tableDate: ").append(toIndentedString(tableDate)).append("\n");
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
