package com.dw.jdbcproject.repository;

import java.util.List;
import java.util.Optional;

import com.dw.jdbcproject.model.Member;

public interface MemberRepository {
	
	// 저장
	Member save(Member member);	
	// id로 row찾기
	Optional<Member> findById(Long id);
	// 이름으로 row찾기
	Optional<Member> findByName(String name);
	// 모든 row찾기
	List<Member> findAll();
}
