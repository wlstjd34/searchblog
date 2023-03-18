package com.wlstjd.searchblog.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchWordRepo extends JpaRepository<SearchWordEntity, String> {
    SearchWordEntity findByKeyword(String keyword);

    List<SearchWordEntity> findTop10ByOrderByTimesDesc();
}
