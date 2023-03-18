package com.wlstjd.searchblog.popular;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchWordRepo extends JpaRepository<SearchWordEntity, String> {
    SearchWordEntity findByKeyword(String keyword);
}
