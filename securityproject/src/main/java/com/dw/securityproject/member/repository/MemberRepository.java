package com.dw.securityproject.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.securityproject.member.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Member findByLoginId(String loginId);
}
