package com.wlstjd.searchblog.persist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
class SearchWordRepoTest {

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

    @Test
    @DisplayName("상위 랭킹 검색어 조회 테스트")
    public void getTopRankingKeyWordTest() {
        // given
        searchWordRepo.save(new SearchWordEntity("ab", 1));
        searchWordRepo.save(new SearchWordEntity("cd", 2));
        searchWordRepo.save(new SearchWordEntity("ef", 3));
        searchWordRepo.save(new SearchWordEntity("gh", 4));
        searchWordRepo.save(new SearchWordEntity("ij", 5));
        searchWordRepo.save(new SearchWordEntity("kl", 6));
        searchWordRepo.save(new SearchWordEntity("mn", 7));
        searchWordRepo.save(new SearchWordEntity("op", 8));
        searchWordRepo.save(new SearchWordEntity("qr", 9));
        searchWordRepo.save(new SearchWordEntity("st", 10));
        searchWordRepo.save(new SearchWordEntity("uv", 11));
        searchWordRepo.save(new SearchWordEntity("wx", 12));
        searchWordRepo.save(new SearchWordEntity("yz", 13));

        // when
        List<SearchWordEntity> result = searchWordRepo.findTop10ByOrderByTimesDesc();

        // then
        Assertions.assertEquals(10,     result.size());
        Assertions.assertEquals("gh",   result.get(9).getKeyword());
        Assertions.assertEquals(4,      result.get(9).getTimes());
        Assertions.assertEquals("ij",   result.get(8).getKeyword());
        Assertions.assertEquals(5,      result.get(8).getTimes());
        Assertions.assertEquals("kl",   result.get(7).getKeyword());
        Assertions.assertEquals(6,      result.get(7).getTimes());
        Assertions.assertEquals("mn",   result.get(6).getKeyword());
        Assertions.assertEquals(7,      result.get(6).getTimes());
        Assertions.assertEquals("op",   result.get(5).getKeyword());
        Assertions.assertEquals(8,      result.get(5).getTimes());
        Assertions.assertEquals("qr",   result.get(4).getKeyword());
        Assertions.assertEquals(9,      result.get(4).getTimes());
        Assertions.assertEquals("st",   result.get(3).getKeyword());
        Assertions.assertEquals(10,     result.get(3).getTimes());
        Assertions.assertEquals("uv",   result.get(2).getKeyword());
        Assertions.assertEquals(11,     result.get(2).getTimes());
        Assertions.assertEquals("wx",   result.get(1).getKeyword());
        Assertions.assertEquals(12,     result.get(1).getTimes());
        Assertions.assertEquals("yz",   result.get(0).getKeyword());
        Assertions.assertEquals(13,     result.get(0).getTimes());
    }
}