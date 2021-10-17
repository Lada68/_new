package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.PrincipalMapper;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.*;
import com.amr.project.service.abstracts.ReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserPageRestController {

    private final PrincipalMapper principalMapper;
    private final UserDao userDao; // надо будет вметсо этого потом добавить в ReadWriteServices getbyname
    private final ReadWriteService<Image,Long> rwImage; // ВРЕМЕННО - потом разобраться с фото пользователей

    @GetMapping("/users/principal")
    public Object getUserPrincipal(Principal principal) {

        User userPrincipal = userDao.findUserByUsername(principal.getName()); // надо будет вметсо этого потом добавить в ReadWriteServices getbyname

        Image image = rwImage.getByKey(Image.class, 1L).orElse(null); // ВРЕМЕННО - потом разобраться с фото пользователей
        userPrincipal.setImages(List.of(image));                               // ВРЕМЕННО - потом разобраться с фото пользователей

        return principalMapper.userToPrincipalDto(userPrincipal);

    }
}



