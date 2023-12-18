package com.dw.discord.jwtauthority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.discord.jwtauthority.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
