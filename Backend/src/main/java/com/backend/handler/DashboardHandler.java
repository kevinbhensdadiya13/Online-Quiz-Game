package com.backend.handler;

import com.backend.dto.request.GlobarSearchRequest;
import com.backend.dto.response.QuizLibraryResponseRecord;
import com.backend.mapper.QuizMapper;
import com.backend.service.DashboardService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class DashboardHandler {
  private static final Logger log = LoggerFactory.getLogger(DashboardHandler.class);

  private final DashboardService dashboardService;

  public DashboardHandler(DashboardService dashboardService) {

    this.dashboardService = dashboardService;
  }

  public Page<QuizLibraryResponseRecord> filter(GlobarSearchRequest request) {
    log.info("Starting filter method with request: {}", request);

    List<QuizLibraryResponseRecord> quizzes = dashboardService.filter(request);

    log.debug("Filtered quizzes: {}", quizzes);

    Page<QuizLibraryResponseRecord> quizPage =
        new PageImpl<>(quizzes, PageRequest.of(request.page(), request.pageSize()), quizzes.size());

    log.info("Returning quiz page: {}", quizPage);

    return quizPage;
  }
}
