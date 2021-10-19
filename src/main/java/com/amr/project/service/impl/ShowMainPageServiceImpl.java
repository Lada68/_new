package com.amr.project.service.impl;

import com.amr.project.converter.MainPageShowConverter;
import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.dao.abstracts.ItemMainPageDao;
import com.amr.project.dao.abstracts.ShopMainPageDao;
import com.amr.project.model.dto.ShowMainPageDTO;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.ShowMainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShowMainPageServiceImpl implements ShowMainPageService {

    private final ItemMainPageDao itemMainPageDao;
    private final ShopMainPageDao shopMainPageDao;
    private final CategoryDao categoryDao;
    private final MainPageShowConverter mainPageShowConverter;

    @Autowired
    public ShowMainPageServiceImpl(ItemMainPageDao itemMainPageDao, ShopMainPageDao shopMainPageDao,
                                   CategoryDao categoryDao, MainPageShowConverter mainPageShowConverter) {
        this.itemMainPageDao = itemMainPageDao;
        this.shopMainPageDao = shopMainPageDao;
        this.categoryDao = categoryDao;
        this.mainPageShowConverter = mainPageShowConverter;
    }

    @Override
    public ShowMainPageDTO showSearch(String s) {

        return new ShowMainPageDTO(
                mainPageShowConverter.shopListToListShopMainPageDTO(shopMainPageDao.searchShops(s)),
                mainPageShowConverter.itemListToListItemMainPageDTO(itemMainPageDao.searchItems(s)),
                mainPageShowConverter.categoryListToListCategoryMainPageDTO(categoryDao.getAll(Category.class)),
                "Поиск товаров",
                "Поиск магазинов"
        );
    }

    @Override
    public ShowMainPageDTO show() {
        return new ShowMainPageDTO(
                mainPageShowConverter.shopListToListShopMainPageDTO(shopMainPageDao.findPopularShops()),
                mainPageShowConverter.itemListToListItemMainPageDTO(itemMainPageDao.findPopularItems()),
                mainPageShowConverter.categoryListToListCategoryMainPageDTO(categoryDao.getAll(Category.class)),
                "Подборка популярных товаров",
                "Подборка популярных магазинов"
        );
    }
}
