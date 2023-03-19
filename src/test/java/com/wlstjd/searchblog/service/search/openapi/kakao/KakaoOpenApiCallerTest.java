package com.wlstjd.searchblog.service.search.openapi.kakao;

import com.wlstjd.searchblog.service.search.openapi.OpenApiCaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class KakaoOpenApiCallerTest {
    @Autowired
    private OpenApiCaller openApiCaller;
    @Value("${kakao.api.url}")
    private String apiUrl;
    @Value("${kakao.api.token}")
    private String token;
    @Test
    @DisplayName("기본 API 호출에 대한 결과 테스트")
    public void requestAPISuccessTest() throws SocketTimeoutException {
        // when
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        String result = openApiCaller.get(header, "GET", apiUrl + "?query=abc");
        // then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.startsWith("{\"documents\""));
    }

    @Test
    @DisplayName("API 호출 실패에 대한 결과 테스트")
    public void requestAPIResponseFailTest() throws SocketTimeoutException {
        // when
        Map<String, String> header = new HashMap<>();
        String result = openApiCaller.get(header, "GET", apiUrl + "");
        // then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.startsWith("{\"errorType\":"));
    }

    @Test
    @DisplayName("잘못된 프로토콜 호출대한 예외 호출 테스트")
    public void requestAPIProtocolFailTest() {
        // when
        Map<String, String> header = new HashMap<>();
        Exception exception = assertThrows(RuntimeException.class,
                () -> openApiCaller.get(header, "GET", "hddps://dapi.kakao.com/v2/search/blog"));
        // then
        Assertions.assertEquals("Wrong Protocol. Expected : http", exception.getMessage());
    }

    @Test
    @DisplayName("잘못된 주소 호출대한 예외 호출 테스트")
    public void requestAPIAddressFailTest() {
        // when
        Map<String, String> header = new HashMap<>();
        Exception exception = assertThrows(RuntimeException.class,
                () -> openApiCaller.get(header, "GET", "https://244.244.244.244"));
        // then
        Assertions.assertEquals("IOException", exception.getMessage());
    }
}