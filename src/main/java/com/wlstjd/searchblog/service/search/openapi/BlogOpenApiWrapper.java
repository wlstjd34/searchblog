package com.wlstjd.searchblog.service.search.openapi;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.kakao.dto.OpenApiResponse;

import java.util.Map;
import java.util.stream.Collectors;

public interface BlogOpenApiWrapper {
    OpenApiResponse search(String query, Sorting sorting, Integer page, Integer size);

    default String makeQuery(Map<String, String> elements) {
        StringBuilder builder = new StringBuilder();
        if (elements == null || elements.isEmpty()) {
            return "";
        }
        String bodyString = elements.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        return builder.append("?").append(bodyString).toString();
    }
}
