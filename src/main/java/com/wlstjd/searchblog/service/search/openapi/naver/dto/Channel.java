package com.wlstjd.searchblog.service.search.openapi.naver.dto;

import java.time.LocalDateTime;
import java.util.List;

public record Channel(LocalDateTime lastBuildDate, Integer total, Integer start, Integer display, List<Item> items) {
}
