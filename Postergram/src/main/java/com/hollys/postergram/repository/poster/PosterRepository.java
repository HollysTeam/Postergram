package com.hollys.postergram.repository.poster;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hollys.postergram.entity.poster.Poster;

public interface PosterRepository extends JpaRepository<Poster, Integer> {
}
