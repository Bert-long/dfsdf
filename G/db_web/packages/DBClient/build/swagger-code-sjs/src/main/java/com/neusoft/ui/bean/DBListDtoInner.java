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
 * DBListDtoInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-16T11:49:18.399439400+08:00[Asia/Shanghai]")
public class DBListDtoInner   {
  @JsonProperty("databaseName")
  private String databaseName = null;

  public DBListDtoInner databaseName(String databaseName) {
    this.databaseName = databaseName;
    return this;
  }

  /**
   * db name
   * @return databaseName
  **/
  @ApiModelProperty(value = "db name")

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DBListDtoInner dbListDtoInner = (DBListDtoInner) o;
    return Objects.equals(this.databaseName, dbListDtoInner.databaseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(databaseName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DBListDtoInner {\n");
    
    sb.append("    databaseName: ").append(toIndentedString(databaseName)).append("\n");
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
