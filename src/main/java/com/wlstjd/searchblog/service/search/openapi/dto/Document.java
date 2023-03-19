package com.wlstjd.searchblog.service.search.openapi.dto;

public record Document(String title, String contents, String url, String blogname,
                       String thumbnail, String datetime) {
}
