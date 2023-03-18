package com.wlstjd.searchblog.service.search.openapi.kakao;

import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.kakao.dto.OpenApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogOpenApiWrapperKakaoImplTest {

    @Autowired
    private BlogOpenApiWrapperKakaoImpl openApi;

    @Test
    @DisplayName("Kakao Blog Search에 대한 간단한 질의 테스트")
    public void simpleOpenApiCallTest() {
        OpenApiResponse result = openApi.search("abc", Sorting.ACCURACY, 1, 10);
        Assertions.assertEquals(10, result.documents().size());
    }

    @Test
    @DisplayName("Kakao Blog Search에 대한 간단한 질의 테스트 사이즈 변경")
    public void simpleOpenApiCallLargeSizeTest() {
        OpenApiResponse result = openApi.search("abc", Sorting.ACCURACY, 1, 50);
        Assertions.assertEquals(50, result.documents().size());
    }

    @Test
    @DisplayName("Kakao Blog Search에 대한 간단한 질의 테스트 페이지 변경")
    public void simpleOpenApiCallLargePageTest() {
        OpenApiResponse result = openApi.search("abc", Sorting.ACCURACY, 3, 50);
        Assertions.assertEquals(50, result.documents().size());
    }
}