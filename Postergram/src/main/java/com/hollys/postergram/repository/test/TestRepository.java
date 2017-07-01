package com.hollys.postergram.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hollys.postergram.entity.test.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
