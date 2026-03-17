package com.learnjiava.employee_management.common.httpresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListResponse {
  private Object content;
  private int curPage;
  private int curPageSize;
  private long totalElements;
  private int totalPages;
}
