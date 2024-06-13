package com.example.pwa.service;


import com.example.pwa.model.Blog;
import com.example.pwa.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }
}