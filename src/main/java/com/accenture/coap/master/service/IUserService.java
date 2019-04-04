package com.accenture.coap.master.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.accenture.coap.master.domain.User;
import com.accenture.coap.master.domain.UserRole;

public interface IUserService extends UserDetailsService {
	// User
	Iterable<User> findAllUser();

	User findOneUser(Long user);

	User findUserByName(String name);

	User findUserByNameOrEmail(String name, String email);

	int deleteUser(Long user);

	int saveOrUpdateUser(User u);

	// UserRole
	int saveUserRole(UserRole role);

	Iterable<UserRole> findAllUserRole();

	UserRole findOneUserRole(Long userRoleId);

	void saveOrUpdateUserRole(UserRole userRole);

	void deleteUserRole(Long id);

}
