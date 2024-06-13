package com.example.pwa.model;

import jakarta.persistence.*;


@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idblog")
    private Long idblog;

    @Column(name = "BlogTitle")
    private String blogTitle;

    @Column(name = "BlogSubtitle")
    private String blogSubtitle;

    @Column(name = "BlogText")
    private String blogText;

    @Column(name = "BlogCategory")
    private int blogCategory;

    @Column(name = "ImgPath")
    private String imgPath;

    // Getters and Setters
    public Long getIdblog() {
        return idblog;
    }

    public void setIdblog(Long idblog) {
        this.idblog = idblog;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSubtitle() {
        return blogSubtitle;
    }

    public void setBlogSubtitle(String blogSubtitle) {
        this.blogSubtitle = blogSubtitle;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public int getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(int blogCategory) {
        this.blogCategory = blogCategory;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}