package com.wlstjd.searchblog.service.search.openapi.naver.dto;

import java.util.List;

public record Rss(String lastBuildDate, Integer total, Integer start, Integer display, List<Item> items) {
}
