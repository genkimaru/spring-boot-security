package com.ccsip.coap.master.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import com.ccsip.coap.master.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Page<User> findAll(Pageable pageable);

	User findByIdAndNameAllIgnoringCase(Long userId, String name);

	User findByNameAllIgnoringCase(String name);

	User findByNameOrEmail(String name, String email);

	@EntityGraph(value = "user.all", type = EntityGraphType.FETCH)
	User findByName(String string);
}
