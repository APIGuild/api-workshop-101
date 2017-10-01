package com.tw.api.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Article request model")
public class ArticleRequest {

    @ApiModelProperty(value = "Title", example = "Title Example")
    private String title;
    @ApiModelProperty(value = "Content", example = "Content Example")
    private String content;
    @ApiModelProperty(value = "Tag", example = "Tag Example")
    private String tag;
}
