package com.wlstjd.searchblog.service.search.openapi.kakao.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetaTest {
    @Test
    public void metaGetterTest() {
        Meta meta = new Meta(10, 1, false);

        assertEquals(10,    meta.total_count());
        assertEquals(1,     meta.pageable_count());
        assertFalse(meta.is_end());
    }
}