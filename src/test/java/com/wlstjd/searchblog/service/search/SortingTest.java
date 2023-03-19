package com.wlstjd.searchblog.service.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {
    @Test
    @DisplayName("Sorting 클래스가 문자열 accuracy를 정상적으로 파싱하는지 테스트")
    public void parsingAccuracyTest() {
        Sorting sorting = Sorting.parseStr("accuracy");
        Assertions.assertEquals(Sorting.ACCURACY, sorting);
    }

    @Test
    @DisplayName("Sorting 클래스가 문자열 recency를 정상적으로 파싱하는지 테스트")
    public void parsingRecencyTest() {
        Sorting sorting = Sorting.parseStr("recency");
        Assertions.assertEquals(Sorting.RECENCY, sorting);
    }

    @Test
    @DisplayName("Sorting 클래스가 비정상 문자열을 파싱하면 에러를 발생시키는지 테스트")
    public void parsingErrorThowTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Sorting.parseStr("abc"));
        Assertions.assertEquals("Undefined Sorting Type", exception.getMessage());
    }

    @Test
    @DisplayName("ACCURACY에 대한 문자열을 반환하는지 테스트")
    public void getAccuraryTest() {
        String str = Sorting.ACCURACY.getVar();
        Assertions.assertEquals("accuracy", str);
    }

    @Test
    @DisplayName("RECENCY에 대한 문자열을 반환하는지 테스트")
    public void getRecencyTest() {
        String str = Sorting.RECENCY.getVar();
        Assertions.assertEquals("recency", str);
    }
}