package com.example.response;

import java.util.List;

import com.example.response.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

  private final Status status;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Metadata metadata;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<T> results;

  //data 랑 results 랑 꼭 구분지을 필요는 없습니다.
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Object data;


  @Getter
  @AllArgsConstructor
  private static class Status {
    private int code;
    private String message;
  }

  @Getter
  @AllArgsConstructor
  private static class Metadata {
    private int resultCount = 0;
  }

  public ApiResponse(List<T> results) {
    this.status = new Status(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
    this.metadata = new Metadata(results.size());
    this.results = results;
  }

  public ApiResponse(int code, String message, Object data) {
    this.status = new Status(code, message);
    this.data = data;
  }
}
