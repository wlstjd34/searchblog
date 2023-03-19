package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;
import com.wlstjd.searchblog.service.search.openapi.kakao.BlogOpenApiWrapperKakaoImpl;
import com.wlstjd.searchblog.service.search.openapi.naver.BlogOpenApiWrapperNaverImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Service
@AllArgsConstructor
@Slf4j
public class SearchService {
    private final BlogOpenApiWrapperKakaoImpl kakaoBlogOpenApi;
    private final BlogOpenApiWrapperNaverImpl naverBlogOpenApi;

    public SearchServiceResponse search(String query, Sorting sorting, Integer page, Integer size) {
        OpenApiResponse result;
        try {
            result = kakaoBlogOpenApi.search(query, sorting, page, size);
        } catch (SocketTimeoutException firstTimeOut) {
            log.debug("Time Out Occur in Kakao Blog Search API");
            try {
                result = naverBlogOpenApi.search(query, sorting, page, size);
            } catch (SocketTimeoutException secondTimeOut) {
                throw new RuntimeException("Time Out in Kakao & Naver Blog Search API");
            }
        }

        return new SearchServiceResponse(result.documents(), page, size, result.meta().is_end());
    }
}
