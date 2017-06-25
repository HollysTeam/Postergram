package com.example.postergram.entity.test;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "test_table")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	public static Test create(String name) {
		Test test = new Test();
		test.name = name;
		return test;
	}
}
