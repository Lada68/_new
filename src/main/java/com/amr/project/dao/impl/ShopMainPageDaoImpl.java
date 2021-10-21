package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopMainPageDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopMainPageDaoImpl extends ReadWriteDaoImpl<Shop, Long> implements ShopMainPageDao {

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
}
