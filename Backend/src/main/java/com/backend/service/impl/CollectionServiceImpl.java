package com.backend.service.impl;

import com.backend.constant.Constant;
import com.backend.entity.Collection;
import com.backend.entity.User;
import com.backend.enums.ResultCode;
import com.backend.exception.OnlineQuizGameAppException;
import com.backend.repository.CollectionRepository;
import com.backend.service.CollectionService;
import com.backend.service.QuizService;
import com.backend.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService {
  private final CollectionRepository collectionRepository;
  private final MessageSource messageSource;
  private final QuizService quizService;

  private final UserService userService;

  public CollectionServiceImpl(
      CollectionRepository collectionRepository,
      MessageSource messageSource,
      QuizService quizService,
      UserService userService) {
    this.collectionRepository = collectionRepository;
    this.messageSource = messageSource;
    this.quizService = quizService;
    this.userService = userService;
  }

  @Override
  public Collection create(Collection collection) {
    User user = userService.getById(collection.getUser().getId());
    collection.setUser(user);
    return collectionRepository.save(collection);
  }

  @Override
  public List<Collection> getAll() {
    return collectionRepository.findAll();
  }

  public Collection getById(Long id) {
    return collectionRepository
        .findById(id)
        .orElseThrow(
            () ->
                new OnlineQuizGameAppException(
                    ResultCode.DATA_NOT_FOUND.getCode(),
                    messageSource.getMessage(
                        "foreignkey.notexist",
                        new String[] {Constant.COLLECTION},
                        LocaleContextHolder.getLocale())));
  }

  @Override
  public Collection update(Collection collection) {
    Collection existingCollection = getById(collection.getId());
    existingCollection.setName(collection.getName());
    return collectionRepository.save(existingCollection);
  }

  public void delete(Long id) {
    validate(id);
    collectionRepository.deleteById(id);
  }

  public void validate(Long id) {
    boolean existsByCollectionId = quizService.existsByCollectionId(id);

    if (existsByCollectionId) {
      throw new OnlineQuizGameAppException(
          ResultCode.DATA_NOT_DELETED.getCode(),
          messageSource.getMessage(
              "data.not.delete",
              new String[] {Constant.COLLECTION, Constant.QUIZ},
              LocaleContextHolder.getLocale()));
    }
  }

  public List<Collection> findByUserId(Long userId) {
    userService.getById(userId);
    return Optional.ofNullable(collectionRepository.findByUserId(userId))
        .filter(collections -> !collections.isEmpty())
        .orElseThrow(
            () ->
                new OnlineQuizGameAppException(
                    ResultCode.DATA_NOT_FOUND.getCode(),
                    messageSource.getMessage(
                        "collection.not.found", new String[] {}, LocaleContextHolder.getLocale())));
  }
}
