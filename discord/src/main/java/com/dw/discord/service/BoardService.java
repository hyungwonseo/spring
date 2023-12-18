package com.dw.discord.service;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.dto.BoardDto;
import com.dw.discord.model.Board;

import java.util.List;

public interface BoardService {
    public BaseResponse<Void> createBoard(BoardDto boardDto);
    public BaseResponse<List<Board>> getAllBoard();
    public BaseResponse<Long> deleteBoard(Long id);
}
