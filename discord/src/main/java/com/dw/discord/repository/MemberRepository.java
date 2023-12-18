package com.dw.discord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.discord.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByLoginId(String loginId);
}
