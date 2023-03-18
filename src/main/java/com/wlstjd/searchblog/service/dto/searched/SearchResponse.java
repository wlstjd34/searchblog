package com.wlstjd.searchblog.service.dto.searched;

public record SearchResponse(Links links, int limits, BlogInfo blogInfo, int size, int start) {
}
