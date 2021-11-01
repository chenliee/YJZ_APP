package com.example.myapplication.utils.http;
/**
 * Created by 刘  on 2021/11/1.
 */
public class HttpResult<T> {
  private Boolean status;
  private String message;
  private String errorMsg;
  private int errorCode;
  private T data;

  public Boolean getStatus() {
   return status;
  }

  public void setStatus(Boolean status) {
   this.status = status;
  }

  public String getMessage() {
   return message;
  }

  public void setMessage(String message) {
   this.message = message;
  }

  public String getErrorMsg() {
   return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
   this.errorMsg = errorMsg;
  }

  public int getErrorCode() {
   return errorCode;
  }

  public void setErrorCode(int errorCode) {
   this.errorCode = errorCode;
  }

  public T getData() {
   return data;
  }

  public void setData(T data) {
   this.data = data;
  }
}


