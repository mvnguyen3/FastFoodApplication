package com.vntofu.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vntofu.fastfood.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

		@Query(value="select * from user where username=:name", nativeQuery = true)
		User findByUserName(String name);
}
