package com.wlstjd.searchblog.service;

import com.wlstjd.searchblog.service.dto.popular.PopularList;
import com.wlstjd.searchblog.service.dto.searched.BlogInfo;
import com.wlstjd.searchblog.service.dto.searched.Links;
import com.wlstjd.searchblog.service.dto.searched.SearchResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BlogAppController {

    @ApiOperation(value="블로그 검색", notes="키워드를 통해 해당하는 블로그 리스트를 검색합니다.")
    @PostMapping(value = "/search", produces = { "application/hal+json" })
    public SearchResponse searchBlogLists(@RequestParam(value = "query") String query,
                                          @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                          @RequestParam(value = "isFirst", required = false, defaultValue = "true") Boolean isFirst) {
        return new SearchResponse(new Links("", "", "", "" ,"", ""),
                        0, new BlogInfo(), 0, 0);
    }

    @ApiOperation(value="인기 검색어", notes="인기 검색어의 리스트와 검색 횟수를 반환합니다")
    @GetMapping(value = "/popular", produces = { "application/hal+json" })
    public PopularList getPopularLists() {
        return new PopularList(new ArrayList<>());
    }
}
