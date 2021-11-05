package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private AddressDao addressDao;
//    private final RoleService roleService;
//    private final EmailUtil emailUtil;
//public UserServiceImpl(ReadWriteDao<User, Long> readWriteDao, UserDao userDao, RoleService roleService, EmailUtil emailUtil) {
//    super(readWriteDao);
//    this.userDao = userDao;
//    this.roleService = roleService;
//    this.emailUtil = emailUtil;
//}
//@Override
//public void registerNewUser(User user) {
//    Set<Role> role = new HashSet<>();
//    role.add(roleService.findById(1L));
//    user.setRoles(role);
//    user.setActivationCode(UUID.randomUUID().toString());
//    emailUtil.sendMessage(user.getEmail(), "Это активация",
//            "Для активации перейдите по ссылке \n" +
//                    "http://localhost:8888/activate/" + user.getActivationCode());
//    userDao.persist(user);
//}


    protected UserServiceImpl(ReadWriteDao<User, Long> readWriteDao, UserDao userDao, RoleDao roleDao, AddressDao addressDao) {
        super(readWriteDao);
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.addressDao = addressDao;
    }

    @Override
    public void updateUser(User user) {

        userDao.update(user);
    }

//    @Override
//    public Address findById(Long id) {
//        return addressDao.findById(id);
//    }

    @Override
    public boolean registerNewUser(User user) {
        if (userDao.getByUsername(user.getUsername())) {
            user.addRole(roleDao.getRoleById(2L));
            user.setActivate(true);
            userDao.persist(user);
      return true;  }
  return false;  }

public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
}

    @Override
    public User findUserByActivationCode(String activationCode) {
        return userDao.findUserByActivationCode(activationCode);
    }

    public boolean getByUsername(String username) {
        System.out.println("service " + username);
        return userDao.getByUsername(username);
    }
    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}
