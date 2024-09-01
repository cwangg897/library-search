package com.library;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class NaverBookResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;
}
