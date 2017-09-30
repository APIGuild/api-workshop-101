package com.tw.api.model;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private String id;
    private String name;
    private int age;
    private List<Article> articles;
}
