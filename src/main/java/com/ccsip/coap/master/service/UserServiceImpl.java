package com.ccsip.coap.master.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.ccsip.coap.master.repository.UserRepository;
import com.ccsip.coap.master.repository.UserRoleRepository;
import com.ccsip.coap.master.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccsip.coap.master.domain.User;
//import com.ccsip.coap.master.domain.UserMappingRole;
import com.ccsip.coap.master.domain.UserRole;
//import com.ccsip.coap.master.repository.UserMappingRoleRepository;


@Component("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;

	public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		super();
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	/**
	 * @Override org.springframework.security.core.userdetails.loadUserByUsername
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("UserServiceImpl.loadUserByUsername->" + userName);
		User userInfo = findUserByName(userName);
		if (userInfo == null) {
			throw new UsernameNotFoundException(userName);
		}

		Set<UserRole> roles = userInfo.getRoles();
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (roles == null || roles.isEmpty()) {
			// default role
			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		}
		SecurityUser su = new SecurityUser(userInfo, grantedAuthorities);

		// BCryptPasswordEncoder bCryptPasswordEncoder = new
		// BCryptPasswordEncoder(16);
		// org.springframework.security.core.userdetails.User user = new
		// org.springframework.security.core.userdetails.User(
		// userInfo.getName(),
		// bCryptPasswordEncoder.encode(userInfo.getPassword()), true, true,
		// true, true,
		// grantedAuthorities);
		return su;
	}

	public Iterable<User> findAllUser() {
		return userRepository.findAll();
	}

	public User findOneUser(Long user) {
		return userRepository.findOne(user);
	}

	@Override
	public User findUserByName(String name) {
		return userRepository.findByNameAllIgnoringCase(name);
	}

	@Override
	public User findUserByNameOrEmail(String name, String email) {
		return userRepository.findByNameOrEmail(name, email);
	}

	@Transactional
	public int deleteUser(Long user) {
		User u = userRepository.findOne(user);
		if (u != null) {
			// List<UserMappingRole> roleList = u.getUserMappingRole();
			// for (UserMappingRole mapping : roleList)
			// userMappingRoleRepository.delete(mapping);
			userRepository.delete(u);
			return 1;
		}
		return 0;
	}

	@Transactional
	public int saveOrUpdateUser(User u) {
		if (u != null) {
			// List<UserMappingRole> mappingList = u.getUserMappingRole();
			u = userRepository.save(u);
			// for (UserMappingRole mapping : mappingList) {
			// mapping.setUserId(u.getId());
			// userMappingRoleRepository.save(mapping);
			// }
			return 1;
		}
		return 0;
	}

	@Override
	public int saveUserRole(UserRole role) {
		if (role != null) {
			userRoleRepository.save(role);
			return 1;
		}
		return 0;
	}

	@Override
	public Iterable<UserRole> findAllUserRole() {
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole findOneUserRole(Long userRoleId) {
		return userRoleRepository.findOne(userRoleId);
	}

	@Override
	public void saveOrUpdateUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}

	@Override
	public void deleteUserRole(Long id) {
		userRoleRepository.delete(id);
	}

	public void test(String... id) {
		for (String i : id) {
			System.out.println(i);
		}
		List<UserRole> list = new ArrayList<UserRole>();
		List<UserRole> list2 = new ArrayList<UserRole>();
		list.forEach(list2::add);
	}
}
