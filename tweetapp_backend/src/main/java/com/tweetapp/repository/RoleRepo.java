package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
