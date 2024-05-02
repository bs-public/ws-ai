package com.ai.dto;

public class LlamaResponse {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "LlamaResponse [message=" + message + "]";
  }

}
