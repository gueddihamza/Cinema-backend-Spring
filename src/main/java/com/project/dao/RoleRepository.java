package com.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.ERole;
import com.project.entities.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);

}
