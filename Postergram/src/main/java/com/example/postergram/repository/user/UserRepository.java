package com.example.postergram.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postergram.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}