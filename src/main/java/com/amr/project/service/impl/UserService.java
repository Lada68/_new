package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ReadWriteServiceImpl<User, Long> {
    private UserDao userDao;

    protected UserService(ReadWriteDao<User, Long> readWriteDao, UserDao userDao) {
        super(readWriteDao);
        this.userDao = userDao;
    }


    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
