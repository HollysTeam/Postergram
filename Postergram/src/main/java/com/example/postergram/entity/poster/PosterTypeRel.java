package com.example.postergram.entity.poster;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name="poster_type_rel")
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@EntityListeners(value = { AuditingEntityListener.class })
public class PosterTypeRel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="poster_id")
	private Poster poster;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private PosterType posterType;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	
	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean deleted;
	
	public static PosterTypeRel create(Poster poster, PosterType posterType, boolean deleted){
		PosterTypeRel newPosterTypeRel = new PosterTypeRel();
		
		newPosterTypeRel.poster = poster;
		newPosterTypeRel.posterType = posterType;
		newPosterTypeRel.deleted = deleted;
		
		return newPosterTypeRel;
	}
	
}
