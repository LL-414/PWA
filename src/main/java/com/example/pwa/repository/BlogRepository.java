package com.example.pwa.repository;

import com.example.pwa.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findTop3ByBlogCategoryOrderByIdblogDesc(int blogCategory);
    List<Blog> findByBlogCategoryOrderByIdblogDesc(int blogCategory);
}