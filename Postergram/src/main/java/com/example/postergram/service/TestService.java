package com.example.postergram.service;

import com.example.postergram.entity.test.Test;
import com.example.postergram.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	private TestRepository testRepository;

	public Test find(Long id) {
		return testRepository.findOne(id);
	}
}

