package com.wlstjd.searchblog.persist;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchWordEntity {

    @Id
    private String keyword;

    private Integer times;
}
