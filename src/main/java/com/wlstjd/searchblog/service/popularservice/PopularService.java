package com.wlstjd.searchblog.service.popularservice;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.dto.popular.PopularList;
import com.wlstjd.searchblog.service.dto.popular.SearchWord;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PopularService {
    private final SearchWordRepo searchWordRepo;

    public PopularList getLists() {
        List<SearchWordEntity> top10Keywords = searchWordRepo.findTop10ByOrderByTimesDesc();
        List<SearchWord> popularList = top10Keywords.stream()
                .map(e -> new SearchWord(e.getKeyword(), e.getTimes()))
                .toList();

        return new PopularList(popularList);
    }
}
