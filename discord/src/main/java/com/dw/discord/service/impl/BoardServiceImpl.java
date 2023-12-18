package com.dw.discord.service.impl;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.dto.BoardDto;
import com.dw.discord.enumstatus.ResultCode;
import com.dw.discord.exception.InvalidRequestException;
import com.dw.discord.model.Board;
import com.dw.discord.model.Member;
import com.dw.discord.repository.BoardRepository;
import com.dw.discord.repository.MemberRepository;
import com.dw.discord.service.BoardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public BaseResponse<Void> createBoard(BoardDto boardDto) {
        Member member = memberRepository.findByLoginId(boardDto.getAuthor());
        if (member == null) {
            throw new InvalidRequestException("Invalid author", "글쓰기 권한이 없습니다");
        }
        // Board 객체 생성
        Board board = new Board();
        board.setCreateAt(LocalDateTime.now());
        board.setAuthor(boardDto.getAuthor());
        board.setCategory(boardDto.getCategory());
        board.setText(boardDto.getText());
        board.setTitle(boardDto.getTitle());

        // 리포지토리 저장
        boardRepository.save(board);
        return new BaseResponse<>(
                ResultCode.SUCCESS.name(),
                null,
                "게시글 생성이 완료되었습니다");
    }

    public BaseResponse<List<Board>> getAllBoard() {
        List<Board> boards = boardRepository.findAll();
        if (boards.isEmpty()) {
            throw new InvalidRequestException("Boards empty", "게시글이 존재하지 않습니다");
        }
        return new BaseResponse<>(
                ResultCode.SUCCESS.name(),
                boards,
                ResultCode.SUCCESS.getMsg());
    }

    public BaseResponse<Long> deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new InvalidRequestException(Long.toString(id), "해당 게시글이 존재하지 않습니다");
        }
        boardRepository.deleteById(id);
        return new BaseResponse<>(
                ResultCode.SUCCESS.name(),
                id,
                "게시글 삭제가 완료되었습니다");
    }
}
