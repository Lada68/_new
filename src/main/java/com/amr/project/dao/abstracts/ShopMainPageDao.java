package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopMainPageDao extends ReadWriteDao<Shop, Long> {
    List<Shop> findPopularShops();
    List<Shop> searchShops(String search);

}
