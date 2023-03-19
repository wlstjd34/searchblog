package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.search.openapi.kakao.BlogOpenApiWrapperKakaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.SocketTimeoutException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SearchServiceTest {
    @MockBean
    private BlogOpenApiWrapperKakaoImpl blogOpenApiWrapperKakao;
    @Autowired
    private SearchService searchService;

    @BeforeEach
    public void init() throws SocketTimeoutException {
        when(blogOpenApiWrapperKakao.search(anyString(), any(), anyInt(), anyInt())).thenThrow(SocketTimeoutException.class);
    }

    @Test
    public void kakaoTimeOutTest() {
        // when
        SearchServiceResponse response = searchService.search("abc", Sorting.ACCURACY, 1, 10);

        // then
        Assertions.assertNotNull(response);
    }
}