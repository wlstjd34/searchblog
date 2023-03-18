package com.wlstjd.searchblog.service.popularservice;

import com.wlstjd.searchblog.persist.SearchWordEntity;
import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.dto.popular.PopularList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PopularService {
    private final SearchWordRepo searchWordRepo;

    public PopularList getLists() {
        List<SearchWordEntity> top10Keywords = searchWordRepo.findTop10ByOrderByTimesDesc();

        return new PopularList(new ArrayList<>());
    }
}
