package com.dw.jwtauthority.service;

import java.util.Collections;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.jwtauthority.dto.UserDto;
import com.dw.jwtauthority.exception.DuplicateMemberException;
import com.dw.jwtauthority.exception.NotFoundMemberException;
import com.dw.jwtauthority.model.Authority;
import com.dw.jwtauthority.model.User;
import com.dw.jwtauthority.repository.UserRepository;
import com.dw.jwtauthority.util.SecurityUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername())
        		.orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNickname(userDto.getNickname());
        user.setAuthorities(Collections.singleton(authority));
        user.setActivated(true);

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username)
        		.orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}