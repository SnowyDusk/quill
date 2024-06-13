package com.quill.identity.controller;

import com.quill.api.identity.vo.UserTokenVO;
import com.quill.common.core.response.QuillResponse;
import com.quill.identity.dto.LoginDTO;
import com.quill.identity.dto.RegisterDTO;
import com.quill.identity.service.UserService;
import com.quill.common.security.context.UserHolder;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户登录、注册、退出登录 功能
 * @author: tuberose
 * @date: 2024/6/5 22:31
 */

@RestController
@RequestMapping("/welcome")
@Slf4j
public class WelcomeController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public QuillResponse<Void> register(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return QuillResponse.success();
    }

    @GetMapping("/verificationCode/{email}")
    public QuillResponse<Void> verificationCode(@PathVariable String email) {
        userService.sendRegisterVerificationCode(email);
        return QuillResponse.success();
    }

    @PostMapping("/login")
    public QuillResponse<UserTokenVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        UserTokenVO userTokenVO = userService.login(loginDTO);
        return QuillResponse.success(userTokenVO);
    }

}
