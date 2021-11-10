package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDaoImpl extends ReadWriteDaoImpl<Shop,Long> implements ShopDao {
    @Override
    public Shop findById(Long id) {
        return em.find(Shop.class, id);
    }

    @Override
    public Shop findByName(String name) {
        return em.createQuery("select u from Shop u where u.name=:name", Shop.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Shop> findPopularShops() {
        return em.createQuery("Select u from Shop u order by u.rating DESC", Shop.class)
                .setMaxResults(6)
                .getResultList();
    }


    @Override
    public List<Shop> searchShops(String search) {
        return em.createQuery("select sh from Shop sh where sh.name LIKE :param", Shop.class)
                .setParameter("param", "%" + search + "%")
                .getResultList();
    }

    @Override
    public boolean findByDataShop(Shop shop) {
        Country country = shop.getLocation();
        City city = shop.getCity();
        String phone = shop.getPhone();
        String email = shop.getEmail();
        String name = shop.getName();
        List<Shop> shopList = (List<Shop>) em.createQuery("select c from Shop c where c.location = :country" +
                " and c.city = :city and c.phone =:phone " +
                "and c.email = :email and c.name =:name", Shop.class)
                .setParameter("country", country)
                .setParameter("city", city)
                .setParameter("phone", phone)
                .setParameter("email", email)
                .setParameter("name", name).getResultList();
        if (shopList.size() > 0) {
            return false;

        } else {
            return true;
        }

    }
}
