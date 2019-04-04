package com.ccsip.coap.master.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import com.ccsip.coap.master.domain.User;
import com.ccsip.coap.master.domain.UserRole;
import com.ccsip.coap.master.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Autowired
	private IUserService userService;
	@Autowired
	UserRepository userRepository;

	@Test
	public void testdeleteUser() {

		int suc = this.userService.deleteUser(1L);
		assertThat(suc == 1);
	}

	@Test
	public void saveUser() throws Exception {

		User user = new User();
		user.setName("D");
		user.setPassword("123456");
		user.setEmail("jianglei.chenD@ccsip.com");
		user.setPhone("13000000000");
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(new UserRole(1L, "USER"));
		roles.add(new UserRole(2L, "ADMIN"));
		user.setRoles(roles);
		userRepository.save(user);

	}

	@Test
	public void updateUser() throws Exception {

		User user = new User();
		user.setId(5L);
		user.setName("D");
		user.setPassword("123456");
		user.setEmail("jianglei.chenDD@ccsip.com");
//		user.setPhone("13000000000");

		userRepository.save(user);

	}

	@Test
	public void findUser() throws Exception {

		User u = userRepository.findByName("A");
		System.out.println(u);
//		System.out.println(u.getRoles());

	}
}
