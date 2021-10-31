package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.User;

public interface UserService {

    boolean registerNewUser(User user);
    User findUserByUsername(String username);
    boolean getByUsername(String name);
    void updateUser(User user);

    Address findById(Long id);
}
