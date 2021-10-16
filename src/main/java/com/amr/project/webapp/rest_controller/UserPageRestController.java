package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Gender;
import com.amr.project.service.abstracts.ReadWriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserPageRestController {
    ReadWriteService<User,Long> readWriteService;

    @GetMapping("/users/principal")
    public Object getUserPrincipal(Principal principal) {
        User user = new User();
        user.setUsername("Тест");
        user.setAge(34);
        user.setGender(Gender.MALE);
        user.addAddress(null);
        user.setPhone("12-041-24");
        System.out.println(principal);
        return user;
 //       return  readWriteService.getByKey(User.class,((User) principal).getId()).orElse(null), new UserDto());
    }


//    public Object getUserPrincipal(Principal principal) {
//        return  converter.EntityToDTO(readWriteService.getByKey(User.class,((User) principal).getId()).orElse(null), new UserDto());
//    }
}



