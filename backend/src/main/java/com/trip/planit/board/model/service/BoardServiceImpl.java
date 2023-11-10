package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper boardMapper;

	@Override
	public void writeBoard(BoardRegistDto boardRegistDto) throws SQLException {
		boardMapper.insertBoard(boardRegistDto);
	}

	@Override
	public List<BoardListDto> findAllBoard() throws SQLException {
		return boardMapper.selectAllBoard();
	}

	@Override
	public BoardListDto findBoard(String boardId) throws SQLException {
		return boardMapper.selectBoard(boardId);
	}

	@Override
	public void deleteBoard(String boardId) throws SQLException {
		boardMapper.deleteBoard(boardId);
	}

	@Override
	public void updateBoard(BoardUpdateDto boardUpdateDto) throws SQLException {
		boardMapper.updateBoard(boardUpdateDto);
	}
}
