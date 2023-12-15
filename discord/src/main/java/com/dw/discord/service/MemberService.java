package com.dw.discord.service;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.dto.MemberDto;
import com.dw.discord.dto.MemberLoginDto;

public interface MemberService {

	public BaseResponse<Void> signUp(MemberDto memberDto);
	public BaseResponse<Void> login(MemberLoginDto memberLoginDto);
}
