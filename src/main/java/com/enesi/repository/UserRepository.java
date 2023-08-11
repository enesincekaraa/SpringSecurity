package com.enesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enesi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findFirstByEmail(String email);
	

}
