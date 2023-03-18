package com.wlstjd.searchblog.service.search.openapi;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;
import org.springframework.stereotype.Component;

@Component
public class BlogOpenApiKakaoImpl implements BlogOpenApi {
    @Override
    public OpenApiResponse search(String query, Sorting sorting, Integer limit, Integer start) {
        return null;
    }
}
