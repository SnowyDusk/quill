package com.quill.identity.service.impl;

import com.quill.api.identity.bo.UserPermitBO;
import com.quill.common.core.constant.ResponseStatus;
import com.quill.common.core.exception.QuillException;
import com.quill.identity.repository.UserTokenRepository;
import com.quill.identity.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author: tuberose
 * @date: 2024/6/9 22:55
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserTokenRepository userTokenRepository;

    @Override
    public UserPermitBO queryUserPermit(String userId) {
        try {
            return userTokenRepository.getUserToken(Long.valueOf(userId));
        } catch (NumberFormatException e) {
            throw new QuillException(ResponseStatus.USER_TOKEN_ERROR);
        }
    }
}
