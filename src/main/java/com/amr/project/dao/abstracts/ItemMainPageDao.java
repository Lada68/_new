package com.amr.project.dao.abstracts;


import com.amr.project.model.entity.Item;

import java.util.List;

public interface ItemMainPageDao extends ReadWriteDao<Item, Long> {

    List<Item> findItemsByCategoryId(Long categoryId);
    List<Item> findPopularItems();
    List<Item> searchItems(String search);
}

