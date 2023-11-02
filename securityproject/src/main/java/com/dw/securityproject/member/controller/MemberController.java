package com.dw.securityproject.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dw.securityproject.member.dto.MemberDto;
import com.dw.securityproject.member.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	private MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@PostMapping("signup")
	public ResponseEntity<String> signUp(@RequestBody MemberDto memberDto) {
		return new ResponseEntity<String>(memberService.signUp(memberDto),
				HttpStatus.CREATED);
	}
	
}









