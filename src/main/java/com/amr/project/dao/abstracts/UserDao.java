package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;

public interface UserDao extends ReadWriteDao<User, Long> {

    User findUserByUsername(String username);

    void update(User user);

    boolean getByUsername(String username);
}
