package com.hollys.postergram.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hollys.postergram.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
