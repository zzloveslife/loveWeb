package com.echoes.mysite.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CustomRequestDto {

    private Long id;

    private List<Long> ids;

    private String content;

    private Long tagId;
}
