package com.wlstjd.searchblog.service.search.openapi.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApiWrapper;
import com.wlstjd.searchblog.service.search.openapi.kakao.dto.OpenApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BlogOpenApiWrapperKakaoImpl implements BlogOpenApiWrapper {
    private final BlogOpenApi blogOpenApi;
    @Value("${kakao.api.url}")
    private String apiUrl;
    @Value("${kakao.api.token}")
    private String token;
    @Override
    public OpenApiResponse search(String keyword, Sorting sorting, Integer page, Integer size) {
        Map<String, String> header = collectRequestHeader();
        Map<String, String> requestBody = collectRequestBody(keyword, sorting, page, size);

        String response = blogOpenApi.get(header,"GET", apiUrl + makeQuery(requestBody));
        if (response == null) {
            throw new RuntimeException("API Response Failed");
        }

        return makeResponseInstance(response);
    }

    private static OpenApiResponse makeResponseInstance(String response) {
        OpenApiResponse result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(response, OpenApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json Object Mapper Failed");
        }
        return result;
    }

    private Map<String, String> collectRequestHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        return header;
    }

    private static Map<String, String> collectRequestBody(String keyword, Sorting sorting, Integer page, Integer size) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", keyword);
        requestBody.put("sort", sorting.getVar());
        requestBody.put("page", page.toString());
        requestBody.put("size", size.toString());
        return requestBody;
    }
}
