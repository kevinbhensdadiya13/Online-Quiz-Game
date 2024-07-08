package com.backend.handler;

import com.backend.dto.request.PlayerPinRequest;
import com.backend.dto.request.PlayerQuizRequest;
import com.backend.dto.request.PlayerQuizUpdateRequest;
import com.backend.dto.response.PinVerifyResponse;
import com.backend.dto.response.PlayerQuizResponse;
import com.backend.entity.PlayerQuiz;
import com.backend.mapper.PlayerQuizMapper;
import com.backend.projection.PlayerQuizQuestionTypeCount;
import com.backend.service.PlayerQuizService;
import com.backend.service.QuestionService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PlayerQuizHandler {
  private final PlayerQuizMapper playerQuizMapper;
  private final PlayerQuizService playerQuizService;
  private final QuestionService questionService;

  /**
   * Constructs a new PlayerQuizHandler with necessary dependencies.
   *
   * @param playerQuizMapper The mapper used for converting between DTOs and the entity.
   * @param playerQuizService The service responsible for the business logic of player quizzes.
   */
  public PlayerQuizHandler(
      PlayerQuizMapper playerQuizMapper,
      PlayerQuizService playerQuizService,
      QuestionService questionService) {
    this.playerQuizMapper = playerQuizMapper;
    this.playerQuizService = playerQuizService;
    this.questionService = questionService;
  }

  /**
   * Handles the creation of a PlayerQuiz based on the provided request.
   *
   * <p>This method maps the {@link PlayerQuizRequest} to a {@link PlayerQuiz} entity, passes it to
   * the service layer for business processing, and then maps the entity back to a {@link
   * PlayerQuizResponse} to be returned to the caller.
   *
   * @param request The {@link PlayerQuizRequest} containing the details for the new quiz.
   * @return The {@link PlayerQuizResponse} containing the details of the created quiz.
   */
  public PlayerQuizResponse create(PlayerQuizRequest request) {
    PlayerQuiz playerQuiz = playerQuizMapper.toEntity(request);
    return playerQuizMapper.toResponse(playerQuizService.create(playerQuiz));
  }

  public PlayerQuizResponse getById(Long id) {
    PlayerQuiz playerQuiz = playerQuizService.getById(id);
    Long questionCountByQuiz = questionService.getQuestionCountByQuiz(playerQuiz.getQuiz().getId());
    List<PlayerQuizQuestionTypeCount> questionTypeCounts =
        questionService.getCountByQuestionType(playerQuiz.getQuiz().getId());
    return playerQuizMapper.toPlayerQuizDetails(
        playerQuiz, questionCountByQuiz, questionTypeCounts);
  }

  public List<PlayerQuizResponse> getAll(Long userID) {
    return playerQuizMapper.toList(playerQuizService.getAll(userID));
  }

  public PlayerQuizResponse update(PlayerQuizUpdateRequest updateRequest) {
    PlayerQuiz playerQuiz = playerQuizMapper.toEntity(updateRequest);
    return playerQuizMapper.toResponse(playerQuizService.update(playerQuiz));
  }

  public void delete(Long id) {
    playerQuizService.delete(id);
  }

  public PinVerifyResponse verifyPin(PlayerPinRequest playerPinRequest) {
    return playerQuizService.verifyPin(playerPinRequest);
  }
}
