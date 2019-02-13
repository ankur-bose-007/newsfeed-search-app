package com.mocktest.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mocktest.core.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	
}
