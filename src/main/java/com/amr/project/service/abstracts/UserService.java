package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

public interface UserService {

    void registerNewUser(User user);
    User findUserByUsername(String username);
}
