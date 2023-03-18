package com.wlstjd.searchblog.service;

import com.wlstjd.searchblog.service.popular.dto.PopularList;
import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.popular.PopularService;
import com.wlstjd.searchblog.service.search.SearchService;
import com.wlstjd.searchblog.service.search.Sorting;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
@RequestMapping
@RequiredArgsConstructor
public class BlogAppController {
    private final SearchService searchService;
    private final PopularService popularService;

    @ApiOperation(value="블로그 검색", notes="키워드를 통해 해당하는 블로그 리스트를 검색합니다.")
    @PostMapping(value = "/search", produces = { "application/hal+json" })
    public EntityModel<SearchServiceResponse> searchBlogLists(@RequestParam(value = "query") String query,
                                                              @RequestParam(value = "sorting", required = false, defaultValue = "accuracy") String sorting,
                                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                              @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                              @RequestParam(value = "isFirst", required = false, defaultValue = "true") Boolean isFirst) {
        SearchServiceResponse result = searchService.search(query, Sorting.parseStr(sorting), page, size, isFirst);

        List<Link> linkList = new ArrayList<>();
        linkList.add(linkTo(methodOn(BlogAppController.class).searchBlogLists(query, sorting, page, size, false)).withSelfRel());
        linkList.add(linkTo(methodOn(BlogAppController.class).searchBlogLists(query, "accuracy", page, size, false)).withRel("accuracy"));
        linkList.add(linkTo(methodOn(BlogAppController.class).searchBlogLists(query, "recency", page, size, false)).withRel("recency"));
        if (page > 1) {
            linkList.add(linkTo(methodOn(BlogAppController.class).searchBlogLists(query, sorting, page - 1, size, false)).withRel("prev"));
        }
        if (!result.isEnd()) {
            linkList.add(linkTo(methodOn(BlogAppController.class).searchBlogLists(query, sorting, page + 1, size, false)).withRel("next"));
        }

        return EntityModel.of(result, linkList);
    }

    @ApiOperation(value="인기 검색어", notes="인기 검색어의 리스트와 검색 횟수를 반환합니다")
    @GetMapping(value = "/popular", produces = { "application/hal+json" })
    public PopularList getPopularLists() {
        return popularService.getLists();
    }
}
