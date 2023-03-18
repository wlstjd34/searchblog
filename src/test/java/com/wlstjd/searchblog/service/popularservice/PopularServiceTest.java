package com.wlstjd.searchblog.service.popularservice;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.dto.popular.PopularList;
import com.wlstjd.searchblog.service.dto.popular.SearchWord;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PopularServiceTest {

    @Autowired
    private PopularService popularService;

    @Autowired
    private SearchWordRepo searchWordRepo;


    @BeforeAll
    public void insert() {
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
    }

    @AfterAll
    public void clear() {
        searchWordRepo.deleteAll();
    }

    @Test
    @DisplayName("입력된 검색어 중에서 상위 검색어 리스트를 가져오는지 테스트합니다")
    public void getRankingKeyWordTest() {
        PopularList popularList = popularService.getLists();
        List<SearchWord> result = popularList.popularList();

        Assertions.assertEquals(10,     result.size());
        Assertions.assertEquals("gh",   result.get(9).keyword());
        Assertions.assertEquals(4,      result.get(9).times());
        Assertions.assertEquals("ij",   result.get(8).keyword());
        Assertions.assertEquals(5,      result.get(8).times());
        Assertions.assertEquals("kl",   result.get(7).keyword());
        Assertions.assertEquals(6,      result.get(7).times());
        Assertions.assertEquals("mn",   result.get(6).keyword());
        Assertions.assertEquals(7,      result.get(6).times());
        Assertions.assertEquals("op",   result.get(5).keyword());
        Assertions.assertEquals(8,      result.get(5).times());
        Assertions.assertEquals("qr",   result.get(4).keyword());
        Assertions.assertEquals(9,      result.get(4).times());
        Assertions.assertEquals("st",   result.get(3).keyword());
        Assertions.assertEquals(10,     result.get(3).times());
        Assertions.assertEquals("uv",   result.get(2).keyword());
        Assertions.assertEquals(11,     result.get(2).times());
        Assertions.assertEquals("wx",   result.get(1).keyword());
        Assertions.assertEquals(12,     result.get(1).times());
        Assertions.assertEquals("yz",   result.get(0).keyword());
        Assertions.assertEquals(13,     result.get(0).times());
    }
}