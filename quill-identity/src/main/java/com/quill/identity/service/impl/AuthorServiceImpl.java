package com.quill.identity.service.impl;

import com.quill.api.identity.constant.AuthorStatus;
import com.quill.common.core.constant.ResponseStatus;
import com.quill.common.core.exception.QuillException;
import com.quill.common.security.context.UserHolder;
import com.quill.common.util.SnowFlake;
import com.quill.identity.dto.AuthorRegisterDTO;
import com.quill.identity.mapper.AuthorMapper;
import com.quill.identity.model.Author;
import com.quill.identity.service.AuthorService;
import com.quill.identity.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @description: TODO
 * @author: tuberose
 * @date: 2024/6/15 23:34
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private AuthorMapper authorMapper;

    @Resource
    private UserService userService;

    @Override
    public void register(AuthorRegisterDTO authorRegisterDTO) {

        // 系统内作者不允许同名 (出版社提交书籍允许作者同名)
        Author author = authorMapper.selectByName(authorRegisterDTO.getName());
        if (author != null) {
            throw new QuillException(ResponseStatus.AUTHOR_NAME_EXIST);
        }

        // 插入作者表
        author = new Author(SnowFlake.nextId(), UserHolder.get().getUserId(),
                authorRegisterDTO.getName(), AuthorStatus.ACTIVE,
                LocalDateTime.now(), LocalDateTime.now());
        authorMapper.insert(author);

        // 修改用户表 identity 字段
        userService.activeAuthorIdentity(UserHolder.get().getUserId());
    }
}
