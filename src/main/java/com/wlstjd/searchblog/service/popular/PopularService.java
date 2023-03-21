package com.wlstjd.searchblog.service.popular;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.popular.dto.PopularList;
import com.wlstjd.searchblog.service.popular.dto.SearchWord;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PopularService {
    private final SearchWordRepo searchWordRepo;

    public PopularList getLists(int size) {
        List<SearchWordEntity> top10Keywords = searchWordRepo.findAllByOrderByTimesDesc(PageRequest.of(0, size));
        List<SearchWord> popularList = top10Keywords.stream()
                .map(e -> new SearchWord(e.getKeyword(), e.getTimes()))
                .toList();

        return new PopularList(popularList);
    }
}
