package com.fjndljh.repository;

import com.fjndljh.entity.User;

public interface UserRepository {
    public int save(User user);
    public User findByName(String name);
    public User findByUser(User user);
}
