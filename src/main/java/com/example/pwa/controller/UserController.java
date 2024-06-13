package com.example.pwa.controller;

import com.example.pwa.model.Blog;
import com.example.pwa.model.User;
import com.example.pwa.service.UserService;
import com.example.pwa.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.support.SessionStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@SessionAttributes("username")
public class UserController {

    private final UserService userService;
    private final BlogService blogService;

    @Autowired
    public UserController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @ModelAttribute("username")
    public String setUpUserSession() {
        return "";
    }

    @GetMapping("/profile")
    public String showLoginAndRegisterForm(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("user", new User());
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("registerMessage", "Username already exists!");
            return "login";
        }
        userService.saveUser(user);
        model.addAttribute("registerMessage", "User registered successfully!");
        return "redirect:/profile";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, @ModelAttribute("username") String username) {
        if (userService.authenticate(user.getUsername(), user.getPassword())) {
            model.addAttribute("username", user.getUsername());
            return "redirect:/addBlog";
        } else {
            model.addAttribute("loginMessage", "Invalid username or password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/profile";
    }

    @GetMapping("/addBlog")
    public String showBlogForm(Model model, @ModelAttribute("username") String username) {
        if (username.isEmpty()) {
            return "redirect:/profile";
        }
        model.addAttribute("blog", new Blog());
        model.addAttribute("username", username);
        return "addBlog";
    }

    @PostMapping("/addBlog")
    public String addBlog(@RequestParam("title") String title,
                          @RequestParam("subtitle") String subtitle,
                          @RequestParam("text") String text,
                          @RequestParam("category") int category,
                          @RequestParam("image") MultipartFile image,
                          @ModelAttribute("username") String username) {
        if (username.isEmpty()) {
            return "redirect:/profile";
        }

        String fileName = image.getOriginalFilename();
        String uploadDir = "uploads/";

        try {
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(image.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        Blog blog = new Blog();
        blog.setBlogTitle(title);
        blog.setBlogSubtitle(subtitle);
        blog.setBlogText(text);
        blog.setBlogCategory(category);
        blog.setImgPath(uploadDir + fileName);

        blogService.saveBlog(blog);

        return "redirect:/blogs";
    }
}
