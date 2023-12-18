package com.dw.discord.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.dw.discord.enumstatus.ResultCode;
import com.dw.discord.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.dto.MemberDto;
import com.dw.discord.dto.MemberLoginDto;
import com.dw.discord.enumstatus.Gender;
import com.dw.discord.model.Member;
import com.dw.discord.repository.MemberRepository;
import com.dw.discord.service.MemberService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	@Override
	public BaseResponse<Void> signUp(MemberDto memberDto) {
		Member member = memberRepository.findByLoginId(memberDto.getLoginId());
		if (member != null) {
			throw new InvalidRequestException(memberDto.getLoginId(), "이미 등록된 ID입니다");
		}		
		member = new Member();
		member.setId(null);
		member.setLoginId(memberDto.getLoginId());
		member.setBirthDate(LocalDate.parse(memberDto.getBirthDate(), 
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		member.setEmail(memberDto.getEmail());
		member.setGender(Gender.valueOf(memberDto.getGender()));
		member.setName(memberDto.getName());
		member.setPassword(memberDto.getPassword());
		
		memberRepository.save(member);
		return  new BaseResponse<>(
				ResultCode.SUCCESS.name(),
				null,
				"회원가입이 완료되었습니다");
	}

	@Override
	public BaseResponse<Void> login(MemberLoginDto memberLoginDto) {
		Member member = memberRepository.findByLoginId(memberLoginDto.getLoginId());
		if (member != null && 
				member.getPassword().matches(memberLoginDto.getPassword())) {
			return new BaseResponse<>(
					ResultCode.SUCCESS.name(),
					null,
					"로그인이 성공했습니다");
		}else {
			throw new InvalidRequestException("Invalid ID / Password", "ID 또는 Password가 올바르지 않습니다");
		}		
	}
}








