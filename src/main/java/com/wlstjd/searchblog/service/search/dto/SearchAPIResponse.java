package com.wlstjd.searchblog.service.search.dto;

import com.wlstjd.searchblog.service.search.openapi.kakao.dto.Document;

import java.util.List;

public record SearchAPIResponse(Links links, int limits, List<Document> documents, int size, int start) {
}
