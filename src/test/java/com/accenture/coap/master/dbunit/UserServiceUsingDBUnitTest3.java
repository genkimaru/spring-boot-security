package com.accenture.coap.master.dbunit;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.accenture.coap.master.domain.User;
import com.accenture.coap.master.domain.UserRole;
import com.accenture.coap.master.metadata.dbunit.DBUnitBase;
import com.accenture.coap.master.service.IUserService;

public class UserServiceUsingDBUnitTest3 extends DBUnitBase {

	@Autowired
	private IUserService userService;

	private static final String TABLE = "user";

	private String origin_path = "/user/origin.xml";

	@Override
	public void setConf() {
		super.table = new String[] { "user" };
	}

	@Test
	public void saveUser() throws Exception {
		clearTable(TABLE);

		User user = new User();
		user.setName("A");
		user.setPassword("123456");
		user.setEmail("jianglei.chen@accenture.com");
		user.setPhone("13000000000");
		userService.saveOrUpdateUser(user);

		verifyDataSet(TABLE, "select name,password,email,phone from " + TABLE, "/user/add_user.xml");
	}

	@Test
	public void updateUser() throws Exception {

		cleanAndInsert("/user/update_user_origin.xml");

		List<User> users = (List<User>) userService.findAllUser();
		for (User s : users) {
			s.setName("a");
			s.setPassword("12345678");
			userService.saveOrUpdateUser(s);
		}

		verifyDataSet(TABLE, "select name,password,email,phone,creator,created,modified_by,modified from " + TABLE,
				"/user/update_user.xml");
	}

	@Test
	public void removeUser() throws Exception {

		cleanAndInsert("/user/remove_user.xml");

		List<User> users = (List<User>) userService.findAllUser();
		for (User s : users) {
			userService.deleteUser(s.getId());
		}
		verifyEmpty(TABLE);
	}

	@Test
	public void findAll() throws Exception {
		cleanAndInsert("/user/all_user.xml");

		List<User> findAll = (List<User>) userService.findAllUser();

		Assert.assertEquals(2, findAll.size());
	}

}
