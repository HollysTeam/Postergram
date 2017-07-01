package com.hollys.postergram.repository.poster;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hollys.postergram.entity.poster.PosterImage;

public interface PosterImageRepository extends JpaRepository<PosterImage, Integer> {
}
