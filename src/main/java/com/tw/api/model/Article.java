package com.tw.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String id;
    private String title;
    private Author author;
    private String content;
    private String tag;
    private int pageView;
}
