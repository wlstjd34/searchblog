package com.wlstjd.searchblog.service.search.openapi;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;

import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BlogOpenApiWrapper {
    protected final OpenApiCaller openApiCaller;

    public BlogOpenApiWrapper(OpenApiCaller openApiCaller) {
        this.openApiCaller = openApiCaller;
    }

    public OpenApiResponse search(String keyword, Sorting sorting, Integer page, Integer size)
            throws SocketTimeoutException {
        Map<String, String> header = collectRequestHeader();
        Map<String, String> requestBody = collectRequestBody(keyword, sorting, page, size);

        String response = openApiCaller.get(header,"GET", getUri() + makeQuery(requestBody));
        if (response == null) {
            throw new RuntimeException("API Response Failed");
        }

        return makeResponseInstance(response);
    }

    protected abstract String getUri();
    protected abstract OpenApiResponse makeResponseInstance(String response);
    protected abstract Map<String, String> collectRequestHeader();
    protected abstract Map<String, String> collectRequestBody(String keyword, Sorting sorting, Integer page, Integer size);

    public static String makeQuery(Map<String, String> elements) {
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
