package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.search.dto.BlogInfo;
import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {
    private final SearchWordRepo searchWordRepo;
    private final BlogOpenApi blogOpenApi;

    public SearchServiceResponse search(String query, Sorting sorting, Integer limit, Integer start,  Boolean isFirst) {
        if (isFirst) {
            countingWordOfKeyWord(query);
        }

        blogOpenApi.search(query, sorting, limit, start);

        return new SearchServiceResponse(new BlogInfo(), 0, 0);
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
}
