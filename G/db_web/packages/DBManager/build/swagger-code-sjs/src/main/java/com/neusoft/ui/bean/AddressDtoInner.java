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
 * AddressDtoInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-15T13:42:47.228614900+08:00[Asia/Shanghai]")
public class AddressDtoInner   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("url")
  private String url = null;

  public AddressDtoInner id(String id) {
    this.id = id;
    return this;
  }

  /**
   * id
   * @return id
  **/
  @ApiModelProperty(value = "id")

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AddressDtoInner url(String url) {
    this.url = url;
    return this;
  }

  /**
   * url address
   * @return url
  **/
  @ApiModelProperty(value = "url address")

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressDtoInner addressDtoInner = (AddressDtoInner) o;
    return Objects.equals(this.id, addressDtoInner.id) &&
        Objects.equals(this.url, addressDtoInner.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressDtoInner {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
