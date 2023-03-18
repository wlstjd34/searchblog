package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.persist.SearchWordRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchWordRepo searchWordRepo;

    private SearchService searchService;

    @Test
    @DisplayName("기본적인 첫 검색에 대한 테스트")
    public void defaultSearchTest() {
    }
}