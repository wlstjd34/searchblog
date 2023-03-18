package com.wlstjd.searchblog.service.search.openapi;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;

public interface BlogOpenApi {
    OpenApiResponse search(String query, Sorting sorting, Integer limit, Integer start);
}
