package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.ItemMainPageDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemMainPageDaoImpl extends ReadWriteDaoImpl<Item, Long> implements ItemMainPageDao {

    @Override
    public List<Item> findItemsByCategoryId(Long categoryId) {
        return em.createQuery("SELECT u FROM Item u JOIN u.categories i where i.id = :id", Item.class)
                .setParameter("id", categoryId).getResultList();
    }

    @Override
    public List<Item> findPopularItems() {
        return em.createQuery("Select u from Item u order by u.rating DESC", Item.class)
                .setMaxResults(4)
                .getResultList();
    }
}
