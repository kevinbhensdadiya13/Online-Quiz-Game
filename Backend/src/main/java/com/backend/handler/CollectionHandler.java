package com.backend.handler;

import com.backend.dto.request.CollectionRequest;
import com.backend.dto.request.CollectionUpdateRequest;
import com.backend.dto.response.CollectionResponse;
import com.backend.entity.Collection;
import com.backend.mapper.CollectionMapper;
import com.backend.service.CollectionService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CollectionHandler {

  private final CollectionService collectionService;
  private final CollectionMapper collectionMapper;

  public CollectionHandler(CollectionService collectionService, CollectionMapper collectionMapper) {
    this.collectionService = collectionService;
    this.collectionMapper = collectionMapper;
  }

  public CollectionResponse create(CollectionRequest collectionRequest) {
    Collection collection = collectionMapper.toEntity(collectionRequest);
    return collectionMapper.toResponse(collectionService.create(collection));
  }

  public List<CollectionResponse> getAll() {
    return collectionMapper.toList(collectionService.getAll());
  }

  public CollectionResponse getById(Long id) {
    return collectionMapper.toResponse(collectionService.getById(id));
  }

  public CollectionResponse update(CollectionUpdateRequest collectionUpdateRequest) {
    Collection collection = collectionMapper.toEntity(collectionUpdateRequest);
    return collectionMapper.toResponse(collectionService.update(collection));
  }

  public void delete(Long id) {
    collectionService.delete(id);
  }

  public List<CollectionResponse> findByUserId(Long userId) {
    return collectionMapper.toList(collectionService.findByUserId(userId));
  }
}
