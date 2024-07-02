package com.bhavjit.cmpt276.portal.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String username, String password);

}
