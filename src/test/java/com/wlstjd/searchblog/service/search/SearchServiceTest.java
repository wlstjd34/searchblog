package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import com.wlstjd.searchblog.service.search.openapi.kakao.BlogOpenApiWrapperKakaoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchWordRepo searchWordRepo;
    @Test
    @DisplayName("기본적인 첫 검색에 대한 테스트")
    public void defaultSearchTest() {
        BlogOpenApi blogOpenApi = Mockito.mock(BlogOpenApi.class);

        SearchService searchService = new SearchService(searchWordRepo, new BlogOpenApiWrapperKakaoImpl(blogOpenApi));
    }
}