package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApiWrapper;
import com.wlstjd.searchblog.service.search.openapi.kakao.dto.OpenApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {
    private final SearchWordRepo searchWordRepo;
    private final BlogOpenApiWrapper blogOpenApi;

    public SearchServiceResponse search(String query, Sorting sorting, Integer page, Integer size, Boolean isFirst) {
        if (isFirst) {
            countingWordOfKeyWord(query);
        }

        OpenApiResponse result = blogOpenApi.search(query, sorting, page, size);

        return new SearchServiceResponse(result.documents(), page, size, result.meta().is_end());
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
