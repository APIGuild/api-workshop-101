package com.tw.api.contract;

import com.tw.api.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private String id;
    private String title;
    private Author author;
    private String content;
    private String tag;
    private int pageView;
}
