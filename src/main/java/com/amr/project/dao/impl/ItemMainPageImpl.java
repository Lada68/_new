package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.ItemMainPageDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemMainPageImpl extends ReadWriteDaoImpl<Item, Long> implements ItemMainPageDao {

    @Override
    public List<Item> findPopularItems() {
        return em.createQuery("Select u from Item u order by u.rating DESC", Item.class)
                .setMaxResults(4)
                .getResultList();
    }

    @Override
    public List<Item> searchItems(String search) {
        return em.createQuery("Select u from Item u where u.name LIKE :param", Item.class)
                .setParameter("param", "%" + search + "%")
                .getResultList();
    }
}
