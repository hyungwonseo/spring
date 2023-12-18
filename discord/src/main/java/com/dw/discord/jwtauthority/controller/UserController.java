package com.dw.discord.jwtauthority.controller;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.enumstatus.ResultCode;
import com.dw.discord.jwtauthority.dto.UserDto;
import com.dw.discord.jwtauthority.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<UserDto>> signup(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(new BaseResponse<>(
                ResultCode.SUCCESS.name(),
                userService.signup(userDto),
                ResultCode.SUCCESS.getMsg()
        ));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BaseResponse<UserDto>> getCurrentUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(
                        ResultCode.SUCCESS.name(),
                        userService.getCurrentUserWithAuthorities(),
                        ResultCode.SUCCESS.getMsg()
        ));
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<UserDto>> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(new BaseResponse<>(
                        ResultCode.SUCCESS.name(),
                        userService.getUserWithAuthorities(username),
                        ResultCode.SUCCESS.getMsg()
        ));
    }
}