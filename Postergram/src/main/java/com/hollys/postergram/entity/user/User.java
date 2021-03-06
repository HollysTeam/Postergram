package com.hollys.postergram.entity.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.hollys.postergram.entity.poster.Poster;
import com.hollys.postergram.entity.poster.PosterImage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name="user")
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;

	private String name;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Poster> posters = new ArrayList<Poster>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PosterImage> posterImages = new ArrayList<PosterImage>();
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	
	@Column(columnDefinition = "TINYINT", length = 1)
	private boolean deleted;
	
	public static User create(String email, String name, boolean deleted){
		User newUser = new User();
		
		newUser.email = email;
		newUser.name = name;
		newUser.deleted = deleted;
		
		return newUser; 
	}
}
