package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.facade.Facade;

public abstract class BaseController {

	@Autowired
	private Facade facade;

	public Facade getFacade() {
		return facade;
	}

}
