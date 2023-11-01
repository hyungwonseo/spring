package com.dw.jdbcproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dw.jdbcproject.model.Member;

@Repository
@Primary
public class JdbcTemplateMemberRepository implements MemberRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired	
	public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Member> findAll() {
		System.out.println("findAll - Template");
		return jdbcTemplate.query("select * from members", memberRowMapper());
	}

	private RowMapper<Member> memberRowMapper() {
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
	}
	
}













