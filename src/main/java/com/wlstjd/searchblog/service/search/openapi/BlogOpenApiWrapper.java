package com.wlstjd.searchblog.service.search.openapi;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.kakao.dto.OpenApiResponse;

import java.util.Map;

public interface BlogOpenApiWrapper {
    OpenApiResponse search(String query, Sorting sorting, Integer limit, Integer start);

    default String makeQuery(Map<String, String> elements) {
        StringBuilder builder = new StringBuilder();
        if (elements.isEmpty()) {
            return "";
        }
        builder.append("?");
        for (Map.Entry<String, String> elem : elements.entrySet()) {
            builder.append(elem.getKey()).append("=").append(elem.getValue());
        }
        return builder.toString();
    }
}
