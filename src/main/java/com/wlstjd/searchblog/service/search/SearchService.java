package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;
import com.wlstjd.searchblog.service.search.openapi.kakao.BlogOpenApiWrapperKakaoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {
    private final BlogOpenApiWrapperKakaoImpl blogOpenApi;

    public SearchServiceResponse search(String query, Sorting sorting, Integer page, Integer size) {

        OpenApiResponse result = blogOpenApi.search(query, sorting, page, size);

        return new SearchServiceResponse(result.documents(), page, size, result.meta().is_end());
    }
}
