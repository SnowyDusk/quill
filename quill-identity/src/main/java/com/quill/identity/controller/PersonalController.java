package com.quill.identity.controller;

import com.quill.common.core.response.QuillResponse;
import com.quill.common.security.context.UserHolder;
import com.quill.identity.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tuberose
 * @date: 2024/6/6 11:24
 */
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public QuillResponse<String> test() {
        return QuillResponse.success("success");
    }

    @DeleteMapping("/logout")
    public QuillResponse<Void> logout() {
        userService.logout(UserHolder.get().getUserId());
        return QuillResponse.success();
    }

    @DeleteMapping("/logoff")
    public QuillResponse<Void> logoff() {
        userService.logoff(UserHolder.get().getUserId());
        return QuillResponse.success();
    }
}
