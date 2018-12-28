package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Role;
import com.project.repository.RoleRepository;
import com.project.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}

}
