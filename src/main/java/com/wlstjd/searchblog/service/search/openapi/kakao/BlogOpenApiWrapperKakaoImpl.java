package com.wlstjd.searchblog.service.search.openapi.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApiWrapper;
import com.wlstjd.searchblog.service.search.openapi.kakao.dto.OpenApiResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BlogOpenApiWrapperKakaoImpl implements BlogOpenApiWrapper {

    private final String API_URL = "https://dapi.kakao.com/v2/search/blog";
    @Override
    public OpenApiResponse search(String keyword, Sorting sorting, Integer limit, Integer start) {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "KakaoAK f5aca1c30f55e20e989e8d0475a92956");
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", keyword);

        String response = BlogOpenApi.get(header,"GET", API_URL + makeQuery(requestBody));

        ObjectMapper objectMapper = new ObjectMapper();
        OpenApiResponse result;
        try {
            result = objectMapper.readValue(response, OpenApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
