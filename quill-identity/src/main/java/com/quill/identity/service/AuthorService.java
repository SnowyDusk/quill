package com.quill.identity.service;

import com.quill.identity.dto.AuthorRegisterDTO;

public interface AuthorService {
    /**
     * 注册作者权限
     */
    void register(AuthorRegisterDTO authorRegisterDTO);
}
