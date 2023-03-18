package com.wlstjd.searchblog.service.search.openapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class BlogOpenApiTest {
    @Test
    @DisplayName("기본 API 호출에 대한 결과 테스트")
    public void simpleRequestAPITest() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "KakaoAK f5aca1c30f55e20e989e8d0475a92956");
        String result = BlogOpenApi.get(header, "GET", "https://dapi.kakao.com/v2/search/blog?query=abc");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.startsWith("{\"documents\""));
    }
}