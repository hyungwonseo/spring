package com.dw.discord.jwtauthority.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.discord.jwtauthority.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	 @EntityGraph(attributePaths = "authorities")
	 Optional<User> findOneWithAuthoritiesByUsername(String username);

	 // 위 메소드와 동일한 메소드 (JPQL 버전)
	@Query("SELECT u FROM User u JOIN FETCH u.authorities WHERE u.username = :username")
	Optional<User> findAuthoritiesByUsername(@Param("username") String username);

}
