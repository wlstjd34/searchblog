package com.wlstjd.searchblog.service.search.openapi.naver.dto;

import java.time.LocalDateTime;

public record Item(String title, String link, String description, String bloggername, String bloggerlink, LocalDateTime postdate) {
}
