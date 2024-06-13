package com.example.pwa.controller;

import com.example.pwa.model.Blog;
import com.example.pwa.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Blog> latestGamingBlogs = blogRepository.findTop3ByBlogCategoryOrderByIdblogDesc(1); // 1 is the category ID for Gaming
        List<Blog> latestTechBlogs = blogRepository.findTop3ByBlogCategoryOrderByIdblogDesc(2);   // 2 is the category ID for Tech
        model.addAttribute("latestGamingBlogs", latestGamingBlogs);
        model.addAttribute("latestTechBlogs", latestTechBlogs);
        return "index";
    }

    @GetMapping("/gaming")
    public String gamingNews(Model model) {
        List<Blog> gamingBlogs = blogRepository.findByBlogCategoryOrderByIdblogDesc(1); // 1 is the category ID for Gaming
        model.addAttribute("gamingBlogs", gamingBlogs);
        return "gaming";
    }

    @GetMapping("/tech")
    public String techNews(Model model) {
        List<Blog> techBlogs = blogRepository.findByBlogCategoryOrderByIdblogDesc(2);   // 2 is the category ID for Tech
        model.addAttribute("techBlogs", techBlogs);
        return "tech";
    }
}