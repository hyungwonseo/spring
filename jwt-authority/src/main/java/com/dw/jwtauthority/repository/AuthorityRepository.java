package com.dw.jwtauthority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.jwtauthority.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
