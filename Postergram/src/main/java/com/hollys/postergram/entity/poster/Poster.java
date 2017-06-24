package com.hollys.postergram.entity.poster;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "type_id")
	private int typeId;

	private String content;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean deleted;

	public static Poster create(int userId, int typeId, String content, boolean deleted) {
		Poster newPoster = new Poster();

		newPoster.userId = userId;
		newPoster.typeId = typeId;
		newPoster.content = content;
		newPoster.deleted = deleted;

		return newPoster;
	}

}
