package com.wlstjd.searchblog.service.search.openapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BlogOpenApiWrapperTest {

    @Test
    @DisplayName("null 매개변수에 대한 Query 제작 테스트")
    public void makeQueryTestByNullParam() {
        String result = BlogOpenApiWrapper.makeQuery(null);
        Assertions.assertEquals("", result);
    }

    @Test
    @DisplayName("빈 매개변수에 대한 Query 제작 테스트")
    public void makeQueryTestByEmptyParam() {
        Map<String, String> param = new HashMap<>();
        String result = BlogOpenApiWrapper.makeQuery(param);
        Assertions.assertEquals("", result);
    }

    @Test
    @DisplayName("매개변수에 대한 Query 제작 테스트")
    public void makeQueryTestByParam() {
        Map<String, String> param = new HashMap<>();
        param.put("key1", "value1");
        param.put("key2", "value2");
        String result = BlogOpenApiWrapper.makeQuery(param);
        Assertions.assertEquals("?key1=value1&key2=value2", result);
    }
}