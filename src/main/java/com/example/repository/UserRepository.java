package com.example.repository;

import com.example.domain.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("SELECT x FROM User x ORDER BY x.username")
	List<User> findAllOrderByName();
	
	@Query("SELECT x FROM User x ORDER BY x.username")
	Page<User> findAllOrderByName(Pageable pageable);
}