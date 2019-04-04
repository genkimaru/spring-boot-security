package com.accenture.coap.master.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.accenture.coap.master.domain.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	Page<UserRole> findAll(Pageable pageable);

	UserRole findById(Long roleId);

}
