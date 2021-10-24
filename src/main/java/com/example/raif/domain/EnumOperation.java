package com.example.raif.domain;

import org.springframework.lang.Nullable;

public enum EnumOperation {
    MORE("moreThan"),
    LESS("lessThan"),
    EQUAL("equal");

    private String title;

    EnumOperation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    @Nullable
    public static EnumOperation getByTitle (String title) {
        for (EnumOperation e : values()) {
            if (e.title.equals(title)) {return e;}
        }
        return null;
    }

}
