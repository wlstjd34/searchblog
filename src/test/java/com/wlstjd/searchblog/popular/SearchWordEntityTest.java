package com.wlstjd.searchblog.popular;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
class SearchWordEntityTest {

    @Autowired
    private SearchWordRepo searchWordRepo;

    @Test
    @DisplayName("검색어의 DB입력 테스트")
    public void saveSearchWordTest() {
        // given
        searchWordRepo.save(new SearchWordEntity("ab", 1));

        // when
        List<SearchWordEntity> result = searchWordRepo.findAll();

        // then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("ab", result.get(0).getKeyword());
        Assertions.assertEquals(1, result.get(0).getTimes());
    }

    @Test
    @DisplayName("검색어 검색 횟수 조회 테스트")
    public void getSearchTimesTest() {
        // given
        searchWordRepo.save(new SearchWordEntity("ab", 1));
        searchWordRepo.save(new SearchWordEntity("cd", 2));

        // when
        SearchWordEntity result = searchWordRepo.findByKeyword("ab");

        // then
        Assertions.assertEquals(1, result.getTimes());

        // when
        SearchWordEntity result2 = searchWordRepo.findByKeyword("cd");

        // then
        Assertions.assertEquals(2, result2.getTimes());
    }
}