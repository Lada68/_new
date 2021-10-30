package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.*;
import com.amr.project.repository.UserRepository;
import com.amr.project.service.abstracts.ReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserPageRestController {

    private final UserMapper userMapper;
    private final ReadWriteService<User,Long> rwUser;
    private final ReadWriteService<Image,Long> rwImage;
    private final UserRepository userRepository;

    @GetMapping("/users/principal")
    public Object getUserPrincipal() {
        User user = rwUser.getByKey (User.class,
                ((User)SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal()).getId()).orElse(null);
        return userMapper.toDto(user);
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody UserDto userDto) {
//        System.out.println(userDto.getFirstName());
//        System.out.println(userMapper.toModel(userDto).getFirstName());
        for (ImageDto imageDto:
                userDto.getImages()) {
            System.out.println(userDto.getGender()+ " DTO "+ imageDto.getId()+ " " + imageDto.getIsMain());
        }
        System.out.println(userDto.getAddress());
        User user = userMapper.toModel(userDto);
    //        List<Image> images = user.getImages();
    //        rwImage.persist(images.get((images.size() - 1)));
        user.setId(userRepository.findUserByUsername(user.getUsername()).orElse(null).getId());
        System.out.println(user.getId());
        for (Image image:
             user.getImages()) {
            System.out.println("USER"+image.getId()+" "+image.getIsMain());
        }
        rwUser.update(user);
        return "{  \"result\" : \"OK\" }";
    }
}



