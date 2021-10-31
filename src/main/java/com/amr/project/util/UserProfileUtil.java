package com.amr.project.util;

import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.User;
import com.amr.project.repository.ImageRepository;
import com.amr.project.repository.UserRepository;
import com.amr.project.service.abstracts.ReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProfileUtil {
    private final UserRepository userRepository;
    private final ReadWriteService<Image, Long> rwImage;

    /////// add userId and persist new Image if any
    public User prepareUser(User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        user.setId(userFromDB.getId());
        if (userFromDB.getImages().size() < user.getImages().size()) {
            int elem = user.getImages().size() - 1;
            Image image = user.getImages().get(elem);
            rwImage.persist(image);
            user.getImages().set(elem, image);
        }
        return user;
    }
}
