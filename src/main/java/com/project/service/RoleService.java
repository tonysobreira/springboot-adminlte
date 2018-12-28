package com.project.service;

import java.util.List;

import com.project.model.Role;

public interface RoleService {

	public List<Role> findAllRole();

	public Role findByRole(String role);

}
