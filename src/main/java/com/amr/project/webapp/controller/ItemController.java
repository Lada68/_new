package com.amr.project.webapp.controller;

import com.amr.project.service.impl.ItemPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    private final ItemPageService itemPageService;

    public ItemController(ItemPageService itemPageService) {
        this.itemPageService = itemPageService;
    }

    @GetMapping("/item/{id}")
    public String itemPage(Model model, @PathVariable(required = true) Long id) {
        model.addAttribute("item", itemPageService.getItem(id));
        model.addAttribute("categories", itemPageService.getCategories());
        return "item";
    }
}
