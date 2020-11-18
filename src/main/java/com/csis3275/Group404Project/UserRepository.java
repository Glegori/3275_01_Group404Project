package com.csis3275.Group404Project;

import com.csis3275.Group404Project.model.USER_404_PROJECT;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * Interface to get user by username for login.
 *
 */
public interface UserRepository extends JpaRepository<USER_404_PROJECT, Integer> {

    Optional<USER_404_PROJECT> findByUserName(String USERNAME);



}
