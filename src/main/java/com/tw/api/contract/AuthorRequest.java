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
@ApiModel(description = "Author request model")
public class AuthorRequest {

    @ApiModelProperty(value = "Name", example = "Yang")
    private String name;
    @ApiModelProperty(value = "Age", example = "30")
    private int age;
}
