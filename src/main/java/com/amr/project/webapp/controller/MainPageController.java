package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.ShowMainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainPageController {

    private final ShowMainPageService showMainPageService;

    @Autowired
    public MainPageController(ShowMainPageService showMainPageService) {
        this.showMainPageService = showMainPageService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        model.addAttribute("mainPageDto", showMainPageService.show());
        return "index";
    }

    @GetMapping("/category/{id}")
    public String showMainCategory(Model model, @PathVariable Long id) {
        model.addAttribute("mainPageDto", showMainPageService.findItemsByCategory(id));
        return "index";
    }
}
