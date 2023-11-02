package com.dw.jdbcproject.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
		// 1. 간단버전 (But, id를 얻어올 수 없음)
//		jdbcTemplate.update("insert into members(name) values(?)",
//				member.getName());
		// 2. SQL 이용한 솔루션
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"insert into members(name) values(?)",
					Statement.RETURN_GENERATED_KEYS
					);
					ps.setString(1, member.getName());
					return ps;
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		member.setId(id);
		// 3. SQL을 사용하지 않는 솔루션
//		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//		jdbcInsert.withTableName("members").usingGeneratedKeyColumns("id");
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("name", member.getName());
//		Number key = jdbcInsert.executeAndReturnKey(
//				new MapSqlParameterSource(parameters));
//		member.setId(key.longValue());
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		List<Member> result = jdbcTemplate.query(
				"select * from members where id = ?", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query(
				"select * from members where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
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













