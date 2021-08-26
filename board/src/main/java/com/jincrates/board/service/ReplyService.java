package com.jincrates.board.service;

import com.jincrates.board.dto.ReplyDTO;
import com.jincrates.board.entity.Board;
import com.jincrates.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO); //댓글의 등록

    List<ReplyDTO> getList(Long bno); //특정 게시글의 댓글 목록

    void modify(ReplyDTO replyDTO); //댓글 수정

    void remove(Long rno); //댓글 삭제

    //ReplyDTO를 Reply 객체로 반환 Board 객체의 처리가 수반됨
    default Reply dtoToEntity(ReplyDTO replyDTO) {

        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }
}
