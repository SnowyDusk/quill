package com.quill.identity.mapper;

import com.quill.identity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectByUserId(@Param("userId") Long userId);

    /**
     * 按照 邮箱 查询用户
     * 邮箱具有唯一性
     */
    User selectByEmail(@Param("email") String email);

    Integer insert(User user);

    Integer deleteByUserId(@Param("userId") Long userId);

    Integer updateIdentity(@Param("userId") Long userId, @Param("identity") Integer identity);
}
