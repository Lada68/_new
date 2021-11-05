package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends ReadWriteDaoImpl<User, Long> implements UserDao {

    @Override
    public User findUserByActivationCode(String activationCode) {
        return (User) em.createQuery("Select e FROM User e WHERE e.activationCode = :activationCode")
                .setParameter("activationCode", activationCode)
                .getSingleResult();
    }
    @Override
    public boolean getByUsername(String username) {
        List<User> listUser = (List<User>) em.createQuery("select uf from User uf where uf.username like :username", User.class).
                setParameter("username", username).getResultList();
        if (listUser.size() > 0) {
            return false;
        } else return true;

    }

    @Override
    public User findUserByUsername(String username) {
        return em.createQuery("select c from User c where c.username like :username", User.class)
                .setParameter("username", username).getSingleResult();

    }
    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(User user) {
        System.out.println(user);
        User userDb = findUserByUsername(user.getUsername());
        System.out.println("dao " + userDb);
        userDb.setAge(user.getAge());
        userDb.setEmail(user.getEmail());
        userDb.setFirstName(user.getFirstName());
        userDb.setGender(user.getGender());
        userDb.setLastName(user.getLastName());
        userDb.setPhone(user.getPhone());
        userDb.setAddress(user.getAddress());
        userDb.setBirthday(user.getBirthday());
        persist(userDb);
    }
}
