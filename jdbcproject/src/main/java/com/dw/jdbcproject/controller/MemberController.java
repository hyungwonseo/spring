package com.dw.jdbcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.jdbcproject.model.Member;
import com.dw.jdbcproject.service.MemberService;

@RestController
public class MemberController {

	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@PostMapping("/member/new")
	public ResponseEntity<Member> saveMember(@RequestBody Member member) {
		return new ResponseEntity<Member>(memberService.saveMember(member),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/member/id/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable long id) {
		return new ResponseEntity<Member>(memberService.getMemberById(id),
				HttpStatus.OK);
	}
	
	@GetMapping("/member/name/{name}")
	public ResponseEntity<Member> getMemberByName(@PathVariable String name) {
		return new ResponseEntity<Member>(memberService.getMemberByName(name),
				HttpStatus.OK);
	}
	
	@GetMapping("/members")
	public ResponseEntity<List<Member>> getAllMembers() {
		return new ResponseEntity<List<Member>>(memberService.getAllMembers(),
				HttpStatus.OK);
	}
}









