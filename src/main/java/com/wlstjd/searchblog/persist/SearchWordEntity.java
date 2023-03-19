package com.wlstjd.searchblog.persist;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "SEARCH_WORD", indexes = @Index(name = "idx_times", columnList = "times"))
public class SearchWordEntity {
    @Id
    private String keyword;

    private Integer times;
}
