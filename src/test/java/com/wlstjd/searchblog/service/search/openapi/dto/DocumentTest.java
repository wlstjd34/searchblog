package com.wlstjd.searchblog.service.search.openapi.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    @Test
    public void documentGetterTest() {
        Document document = new Document("title",  "contents", "url", "blogname",
                "thumbnail", "datetime");
        assertEquals("title",       document.title());
        assertEquals("contents",    document.contents());
        assertEquals("url",         document.url());
        assertEquals("blogname",    document.blogname());
        assertEquals("thumbnail",   document.thumbnail());
        assertEquals("datetime",    document.datetime());
    }
}