package com.backend.service.impl;

import static com.backend.constant.Constant.GLOBAL_SEARCH_QUIZ;
import static com.backend.constant.Constant.GLOBAL_SEARCH_REPORT;

import com.backend.dto.request.GlobarSearchRequest;
import com.backend.dto.response.QuizLibraryResponseRecord;
import com.backend.entity.PlayerQuiz;
import com.backend.entity.Quiz;
import com.backend.mapper.PlayerQuizMapper;
import com.backend.mapper.QuizMapper;
import com.backend.service.DashboardService;
import com.backend.service.PlayerQuizService;
import com.backend.service.QuizService;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {
  private static final Logger log = LoggerFactory.getLogger(DashboardServiceImpl.class);

  private final QuizService quizService;
  private final PlayerQuizService playerQuizService;
  private final QuizMapper quizMapper;
  private final PlayerQuizMapper playerQuizMapper;

  public DashboardServiceImpl(
      QuizService quizService,
      PlayerQuizService playerQuizService,
      QuizMapper quizMapper,
      PlayerQuizMapper playerQuizMapper) {

    this.quizService = quizService;
    this.playerQuizService = playerQuizService;
    this.quizMapper = quizMapper;
    this.playerQuizMapper = playerQuizMapper;
  }

  @Override
  public List<QuizLibraryResponseRecord> filter(GlobarSearchRequest request) {
    if (GLOBAL_SEARCH_QUIZ.equalsIgnoreCase(request.filterType())) {
      Page<Quiz> quizPage = quizService.getQuizBySearchName(request);
      log.debug("Quiz Page Content: {}", quizPage.getContent());
      return quizMapper.toLibraryList(quizPage.getContent());
    } else if (GLOBAL_SEARCH_REPORT.equalsIgnoreCase(request.filterType())) {
      Page<PlayerQuiz> playerQuizPage = playerQuizService.getReportBySearchName(request);
      List<QuizLibraryResponseRecord> libraryList =
          playerQuizMapper.toLibraryList(playerQuizPage.getContent());
      log.debug("PlayerQuiz Page Content: {}", playerQuizPage.getContent());
      return libraryList;
    }
    log.info("Unknown filter type: {}", request.filterType());
    return Collections.emptyList();
  }
}
