package com.wlstjd.searchblog.service.searchservice;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.dto.searched.BlogInfo;
import com.wlstjd.searchblog.service.dto.searched.Links;
import com.wlstjd.searchblog.service.dto.searched.SearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {
    private final SearchWordRepo searchWordRepo;

    public SearchResponse search(String query, Integer limit, Boolean isFirst) {
        countingWordOfKeyWord(query);

        return new SearchResponse(new Links("", "", "", "" ,"", ""),
                0, new BlogInfo(), 0, 0);
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
