package com.wlstjd.searchblog.service.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sorting {
    ACCURACY("accuracy", "sim"), RECENCY("recency", "date");

    private final String kakaoExpr;
    private final String naverExpr;

    public static Sorting parseStr(String str) {
        return switch (str) {
            case "accuracy", "sim" -> ACCURACY;
            case "recency", "date" -> RECENCY;
            default -> throw new IllegalArgumentException("Undefined Sorting Type");
        };
    }
}
