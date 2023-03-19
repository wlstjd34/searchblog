package com.wlstjd.searchblog.service.search.openapi;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class BlogOpenApiWrapper {
    protected final BlogOpenApi blogOpenApi;

    public BlogOpenApiWrapper(BlogOpenApi blogOpenApi) {
        this.blogOpenApi = blogOpenApi;
    }

    public OpenApiResponse search(String keyword, Sorting sorting, Integer page, Integer size) {
        Map<String, String> header = collectRequestHeader();
        Map<String, String> requestBody = collectRequestBody(keyword, sorting, page, size);

        String response = blogOpenApi.get(header,"GET", getUri() + makeQuery(requestBody));
        if (response == null) {
            throw new RuntimeException("API Response Failed");
        }

        return makeResponseInstance(response);
    }

    protected abstract String getUri();
    protected abstract OpenApiResponse makeResponseInstance(String response);
    protected abstract Map<String, String> collectRequestHeader();
    protected abstract Map<String, String> collectRequestBody(String keyword, Sorting sorting, Integer page, Integer size);

    public String makeQuery(Map<String, String> elements) {
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
