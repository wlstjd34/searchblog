package com.wlstjd.searchblog.persist;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SearchWordRepo extends JpaRepository<SearchWordEntity, String> {
    SearchWordEntity findByKeyword(String keyword);

    List<SearchWordEntity> findAllByOrderByTimesDesc(Pageable pageable);
}
