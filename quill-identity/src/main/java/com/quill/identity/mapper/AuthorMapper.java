package com.quill.identity.mapper;

import com.quill.identity.model.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorMapper {
    Integer insert(Author author);

    Integer deleteByUserId(@Param("userId") Long userId);

    Author selectByName(@Param("name") String name);
}
