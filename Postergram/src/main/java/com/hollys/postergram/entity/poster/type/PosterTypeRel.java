package com.hollys.postergram.entity.poster.type;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hollys.postergram.entity.poster.Poster;

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
	
	@OneToOne
	@JoinColumn(name="poster_id")
	private Poster poster;
	
	@OneToOne
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
