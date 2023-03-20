package com.wlstjd.searchblog.service;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.popular.dto.PopularList;
import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.popular.PopularService;
import com.wlstjd.searchblog.service.search.SearchService;
import com.wlstjd.searchblog.service.search.Sorting;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
@RequestMapping
@RequiredArgsConstructor
public class BlogAppController {
    private final SearchService searchService;
    private final PopularService popularService;

    private final SearchWordRepo searchWordRepo;

    @ApiOperation(value="블로그 검색", notes="키워드를 통해 해당하는 블로그 리스트를 검색합니다. 추가로 검색어 횟수를 증가시킵니다.")
    @PostMapping(value = "/search", produces = { "application/hal+json" })
    public RedirectView postSearchBlogLists(RedirectAttributes redirect,
                                            @RequestParam(value = "query") String query,
                                            @RequestParam(value = "sorting", required = false, defaultValue = "accuracy") String sorting,
                                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        countingWordOfKeyWord(query);
        redirect.addAttribute("query", query);
        redirect.addAttribute("sorting", sorting);
        redirect.addAttribute("page", page);
        redirect.addAttribute("size", size);
        return new RedirectView("/search");
    }
    private void countingWordOfKeyWord(String keyWord) {
        SearchWordEntity presentWord = searchWordRepo.findByKeyword(keyWord);
        if (presentWord == null) {
            searchWordRepo.save(new SearchWordEntity(keyWord, 1));
        } else {
            presentWord.setTimes(presentWord.getTimes() + 1);
            searchWordRepo.save(presentWord);
        }
    }


    @ApiOperation(value="블로그 검색", notes="키워드를 통해 해당하는 블로그 리스트를 검색합니다.")
    @GetMapping(value = "/search", produces = { "application/hal+json" })
    public ResponseEntity getSearchBlogLists(@RequestParam(value = "query") String query,
                                                              @RequestParam(value = "sorting", required = false, defaultValue = "accuracy") String sorting,
                                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                              @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        if (!checkValidation(sorting, page, size)) return ResponseEntity.badRequest().build();
        SearchServiceResponse result = searchService.search(query, Sorting.parseStr(sorting), page, size);
        List<Link> linkList = makeLinks(query, sorting, page, size, result);
        return ResponseEntity.ok(EntityModel.of(result, linkList));
    }

    private boolean checkValidation(String sorting, Integer page, Integer size) {
        if (sorting.compareTo("accuracy") != 0 && sorting.compareTo("recency") != 0) return false;
        if (page < 1 || page > 50 || size < 1 || size > 50) return false;
        return true;
    }

    private static List<Link> makeLinks(String query, String sorting, Integer page, Integer size, SearchServiceResponse result) {
        List<Link> linkList = new ArrayList<>();
        linkList.add(linkTo(methodOn(BlogAppController.class).getSearchBlogLists(query, sorting, page, size)).withSelfRel());
        linkList.add(linkTo(methodOn(BlogAppController.class).getSearchBlogLists(query, "accuracy", page, size)).withRel("accuracy"));
        linkList.add(linkTo(methodOn(BlogAppController.class).getSearchBlogLists(query, "recency", page, size)).withRel("recency"));
        if (page > 1) {
            linkList.add(linkTo(methodOn(BlogAppController.class).getSearchBlogLists(query, sorting, page - 1, size)).withRel("prev"));
        }
        if (!result.isEnd()) {
            linkList.add(linkTo(methodOn(BlogAppController.class).getSearchBlogLists(query, sorting, page + 1, size)).withRel("next"));
        }
        return linkList;
    }

    @ApiOperation(value="인기 검색어", notes="인기 검색어의 리스트와 검색 횟수를 반환합니다")
    @GetMapping(value = "/popular", produces = { "application/hal+json" })
    public PopularList getPopularLists() {
        return popularService.getLists();
    }
}
