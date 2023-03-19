package com.wlstjd.searchblog.service.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sorting {
    ACCURACY("accuracy"), RECENCY("recency");

    private final String var;

    public static Sorting parseStr(String str) {
        return switch (str) {
            case "accuracy" -> ACCURACY;
            case "recency" -> RECENCY;
            default -> throw new IllegalArgumentException("Undefined Sorting Type");
        };
    }
}
