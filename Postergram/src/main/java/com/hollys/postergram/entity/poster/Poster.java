package com.hollys.postergram.entity.poster;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hollys.postergram.entity.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "poster")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(value = { AuditingEntityListener.class })
public class Poster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
	private List<PosterLike> posterLikes;
	
	@OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
	private List<PosterImage> posterImages;
	
	@OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
	private List<PosterTypeRel> posterTypeRels;

	private String content;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean deleted;
	
	private boolean addPosterImage(PosterImage posterImage){
		if(this.posterImages == null){
			this.posterImages = new ArrayList<PosterImage>();
		}
		return posterImages.add(posterImage);
	}
	
	public static Poster create(User user, List<PosterImage> posterImages, String content, boolean deleted) {
		Poster newPoster = new Poster();
		
		newPoster.user = user;
		newPoster.posterImages = posterImages;
		newPoster.content = content;
		newPoster.deleted = deleted;

		return newPoster;
	}

}
