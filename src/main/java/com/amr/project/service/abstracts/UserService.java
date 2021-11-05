package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.User;

public interface UserService extends ReadWriteService<User, Long>  {
    //void registerNewUser(User user);

    boolean registerNewUser(User user);
    User findUserByUsername(String username);

    User findUserByActivationCode(String activationCode);

    boolean getByUsername(String name);
    void updateUser(User user);
    User findById(Long id);

    //Address findById(Long id);
}
