package com.example.postergram.repository.poster;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postergram.entity.poster.Poster;

public interface PosterRepository extends JpaRepository<Poster, Integer> {
}
