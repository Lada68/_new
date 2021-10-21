package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.entity.*;
import com.amr.project.service.abstracts.ReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserPageRestController {

    private final UserMapper userMapper;
    private final ReadWriteService<User,Long> rwUser;

    @GetMapping("/users/principal")
    public Object getUserPrincipal() {
        return rwUser.getByKey (User.class,
                ((User)SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal()).getId()).orElse(null);
    }
}



