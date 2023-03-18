package com.wlstjd.searchblog.service.search.dto;

public record SearchServiceResponse(BlogInfo blogInfo, int size, int start) {
}
