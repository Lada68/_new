package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ReadWriteDao<Shop, Long> readWriteDao, ShopDao shopDao) {
        super(readWriteDao);
        this.shopDao = shopDao;
    }

    @Override
    public Shop findById(Long id) {
        return shopDao.findById(id);
    }

    @Override
    public Shop findByName(String name) {
        return shopDao.findByName(name);
    }

    @Override
    public void addNewShop(Shop shop) {
        if (shopDao.findByDataShop(shop)) {
            shop.setModerateAccept(true);
            shopDao.persist(shop);
        }
    }

    @Override
    public void deleteUserShop(Shop shopDb) {
        User user = shopDb.getUser();
        shopDb.setUser(null);
        shopDb.setModerateAccept(false);
        shopDao.update(shopDb);
    }

}
