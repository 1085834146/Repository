package com.fjndljh.repository;

import com.fjndljh.entity.Blog;
import com.fjndljh.entity.User;

import java.util.List;

public interface BlogRepository {
    public int save(Blog blog);
    public List<Blog> findByUser(User user);
    public List<Blog> findAll();
    public Blog findById(long id);
    public int deleteBlog1(long id);
    public int deleteBlog2(Blog blog);
}
