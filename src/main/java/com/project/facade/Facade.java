package com.project.facade;

import java.util.List;

import com.project.model.Role;
import com.project.model.User;

public interface Facade {

	public User findUserByEmail(String email);

	public User saveUser(User user);

	public List<Role> findAllRole();

	public Role findByRole(String role);

	public String encodePassword(CharSequence rawPassword);

	public List<User> findAllUser();

}
