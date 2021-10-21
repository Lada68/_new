package com.amr.project.service.impl;

import com.amr.project.converter.ItemPageMapper;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPageService {
    private final ReadWriteService<Item, Long> itemService;
    private final ReadWriteService<Category, Long> categoryService;
    private final ItemPageMapper itemPageMapper;

    @Autowired
    public ItemPageService(ItemPageMapper itemPageMapper,
                           ReadWriteService<Item, Long> itemService,
                           ReadWriteService<Category, Long> categoryService) {

        this.itemPageMapper = itemPageMapper;
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    public ItemDto getItem(Long id) {
        return itemPageMapper.itemToItemDto(itemService.getByKey(Item.class, id).orElse(new Item()));
    }

    public List<CategoryDto> getCategories() {
        return itemPageMapper.categoriesToCategoriesDto(categoryService.getAll(Category.class));
    }
}
