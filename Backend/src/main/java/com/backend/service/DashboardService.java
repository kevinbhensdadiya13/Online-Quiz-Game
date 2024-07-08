package com.backend.service;

import com.backend.dto.request.GlobarSearchRequest;
import com.backend.dto.response.QuizLibraryResponseRecord;
import java.util.List;

public interface DashboardService {
  List<QuizLibraryResponseRecord> filter(GlobarSearchRequest request);
}
