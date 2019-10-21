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
 * UrlListDtoInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-16T11:49:18.399439400+08:00[Asia/Shanghai]")
public class UrlListDtoInner   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("url")
  private String url = null;

  public UrlListDtoInner id(String id) {
    this.id = id;
    return this;
  }

  /**
   * url id
   * @return id
  **/
  @ApiModelProperty(value = "url id")

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UrlListDtoInner url(String url) {
    this.url = url;
    return this;
  }

  /**
   * url name
   * @return url
  **/
  @ApiModelProperty(value = "url name")

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
    UrlListDtoInner urlListDtoInner = (UrlListDtoInner) o;
    return Objects.equals(this.id, urlListDtoInner.id) &&
        Objects.equals(this.url, urlListDtoInner.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UrlListDtoInner {\n");
    
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
