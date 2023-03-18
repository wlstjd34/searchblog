package com.wlstjd.searchblog.service.search.dto;

public record SearchAPIResponse(Links links, int limits, BlogInfo blogInfo, int size, int start) {
}
