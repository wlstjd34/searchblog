package com.wlstjd.searchblog.service;

import com.wlstjd.searchblog.service.popular.dto.PopularList;
import com.wlstjd.searchblog.service.search.dto.Links;
import com.wlstjd.searchblog.service.search.dto.SearchAPIResponse;
import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.popular.PopularService;
import com.wlstjd.searchblog.service.search.SearchService;
import com.wlstjd.searchblog.service.search.Sorting;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BlogAppController {
    private final SearchService searchService;
    private final PopularService popularService;

    @ApiOperation(value="블로그 검색", notes="키워드를 통해 해당하는 블로그 리스트를 검색합니다.")
    @PostMapping(value = "/search", produces = { "application/hal+json" })
    public SearchAPIResponse searchBlogLists(@RequestParam(value = "query") String query,
                                             @RequestParam(value = "sorting", required = false, defaultValue = "accuracy") String sorting,
                                             @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                             @RequestParam(value = "start", required = false, defaultValue = "10") Integer start,
                                             @RequestParam(value = "isFirst", required = false, defaultValue = "true") Boolean isFirst) {
        SearchServiceResponse result = searchService.search(query, Sorting.parseStr(sorting), limit, start, isFirst);
        return new SearchAPIResponse(new Links("", "", "", "" ,"", ""),
                0, result.blogInfo(), result.size(), result.start());
    }

    @ApiOperation(value="인기 검색어", notes="인기 검색어의 리스트와 검색 횟수를 반환합니다")
    @GetMapping(value = "/popular", produces = { "application/hal+json" })
    public PopularList getPopularLists() {
        return popularService.getLists();
    }
}
