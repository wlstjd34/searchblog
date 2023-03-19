package com.wlstjd.searchblog.service.search.openapi.dto;

import java.time.LocalDateTime;

public record Document(String title, String contents, String url, String blogname,
                       String thumbnail, LocalDateTime datetime) {
}
