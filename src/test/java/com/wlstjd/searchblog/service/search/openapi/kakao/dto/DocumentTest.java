package com.wlstjd.searchblog.service.search.openapi.kakao.dto;

import com.wlstjd.searchblog.service.search.openapi.dto.Document;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    @Test
    public void documentGetterTest() {
        Document document = new Document("title",  "contents", "url", "blogname",
                "thumbnail", LocalDateTime.of(2023, 3, 19, 9 ,19));
        assertEquals("title",       document.title());
        assertEquals("contents",    document.contents());
        assertEquals("url",         document.url());
        assertEquals("blogname",    document.blogname());
        assertEquals("thumbnail",   document.thumbnail());
        assertEquals(LocalDateTime.of(2023, 3, 19, 9 ,19),    document.datetime());
    }
}