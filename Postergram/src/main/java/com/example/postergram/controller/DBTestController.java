package com.example.postergram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DBTestController {
	@RequestMapping(value = "/test/index", method = RequestMethod.GET)
	public String index() {
		return "DBAccessTest";
	}
}
