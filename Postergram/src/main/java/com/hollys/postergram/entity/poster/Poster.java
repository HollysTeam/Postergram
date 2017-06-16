package com.hollys.postergram.entity.poster;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
@Entity
@Table(name = "poster")
public class Poster {
	@Id
	@Column(name = "id",nullable=false)
	@Size(max = 11)
	private int id;
	
	@Column(name = "user_id",nullable=false)
	@Size(max = 11)
	private int userId;
	
	@Column(name = "type_id",nullable=false)
	@Size(max = 11)
	private int typeId;

	@Column(name = "content",nullable=false)
	@Size(max = 500)
	private String content;
	
	@Column(name = "createdAt",nullable=false)
	private Timestamp createdAt;
	
	@Column(name = "modifiedAt",nullable=false)
	private Timestamp modifiedAt;

	@Column(name = "deleted",nullable=false)
	@Size(min = 1, max = 1)
	private int deleted;
	
	public static Poster create(int userId, int typeId, String content, boolean deleted){
		Poster newPoster = new Poster();
		
		newPoster.userId = userId;
		newPoster.typeId = typeId;
		newPoster.content = content;
		newPoster.deleted = 0;
	
		return newPoster;
	}

}
