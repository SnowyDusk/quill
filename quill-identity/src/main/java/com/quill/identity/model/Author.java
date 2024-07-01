package com.quill.identity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: 作者信息
 * @author: tuberose
 * @date: 2024/6/15 23:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Long authorId;

    private Long userId;    // 关联唯一的 uid

    private String name;

    private Integer status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
