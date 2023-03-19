package com.wlstjd.searchblog.persist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchWordEntityTest {
    @Test
    public void entityGetterTest() {
        SearchWordEntity searchWordEntity = new SearchWordEntity("abc", 1);

        Assertions.assertEquals("abc", searchWordEntity.getKeyword());
        Assertions.assertEquals(1, searchWordEntity.getTimes());
    }

    @Test
    public void entitySetterTest() {
        SearchWordEntity searchWordEntity = new SearchWordEntity();

        searchWordEntity.setKeyword("def");
        searchWordEntity.setTimes(2);

        Assertions.assertEquals("SearchWordEntity(keyword=def, times=2)", searchWordEntity.toString());
    }
}