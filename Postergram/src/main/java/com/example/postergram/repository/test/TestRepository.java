package com.example.postergram.repository.test;

import com.example.postergram.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
