package com.dw.securityproject.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.securityproject.common.exception.InvalidInputException;
import com.dw.securityproject.member.dto.MemberDto;
import com.dw.securityproject.member.model.Member;
import com.dw.securityproject.member.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}
	
	public String signUp(MemberDto memberDto) {
		// ID 중복 검사
		Member member = memberRepository.findByLoginId(memberDto.getLoginId());
		if (member != null) {
			//return "이미 등록된 ID입니다.";
			throw new InvalidInputException("loginId", "이미 등록된 ID입니다.");
		}		
		// Member 객체 생성
		member = new Member(
				null,
				memberDto.getLoginId(),
				memberDto.getPassword(),
				memberDto.getName(),
				memberDto.getBirthDate(),
				memberDto.getGender(),
				memberDto.getEmail()
				);		
		// 리포지토리 저장
		memberRepository.save(member);
		return "회원가입이 완료되었습니다.";
	}
}







