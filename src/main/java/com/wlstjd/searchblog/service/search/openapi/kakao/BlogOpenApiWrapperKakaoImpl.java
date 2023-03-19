package com.wlstjd.searchblog.service.search.openapi.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApiWrapper;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BlogOpenApiWrapperKakaoImpl extends BlogOpenApiWrapper {

    @Value("${kakao.api.url}")
    private String apiUrl;
    @Value("${kakao.api.token}")
    private String token;

    public BlogOpenApiWrapperKakaoImpl(BlogOpenApi blogOpenApi) {
        super(blogOpenApi);
    }

    @Override
    protected String getUri() {
        return apiUrl;
    }

    public OpenApiResponse makeResponseInstance(String response) {
        OpenApiResponse result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(response, OpenApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json Object Mapper Failed");
        }
        return result;
    }

    public Map<String, String> collectRequestHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        return header;
    }

    public Map<String, String> collectRequestBody(String keyword, Sorting sorting, Integer page, Integer size) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", keyword);
        requestBody.put("sort", sorting.getKakaoExpr());
        requestBody.put("page", page.toString());
        requestBody.put("size", size.toString());
        return requestBody;
    }
}
