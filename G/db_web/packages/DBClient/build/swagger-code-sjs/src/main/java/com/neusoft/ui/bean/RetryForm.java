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
 * RetryForm
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-16T11:49:18.399439400+08:00[Asia/Shanghai]")
public class RetryForm   {
  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("passWord")
  private String passWord = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("condition")
  private String condition = null;

  public RetryForm userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public RetryForm passWord(String passWord) {
    this.passWord = passWord;
    return this;
  }

  /**
   * Get passWord
   * @return passWord
  **/
  @ApiModelProperty(value = "")

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public RetryForm url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public RetryForm condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * Get condition
   * @return condition
  **/
  @ApiModelProperty(value = "")

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetryForm retryForm = (RetryForm) o;
    return Objects.equals(this.userName, retryForm.userName) &&
        Objects.equals(this.passWord, retryForm.passWord) &&
        Objects.equals(this.url, retryForm.url) &&
        Objects.equals(this.condition, retryForm.condition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, passWord, url, condition);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RetryForm {\n");
    
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    passWord: ").append(toIndentedString(passWord)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
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
