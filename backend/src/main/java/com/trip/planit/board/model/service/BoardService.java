package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;

public interface BoardService {
	void writeBoard(BoardRegistDto boardRegistDto) throws SQLException;
	List<BoardListDto> findAllBoard() throws SQLException;
	BoardListDto findBoard(String boardId) throws SQLException;
	void deleteBoard(String boardId) throws SQLException;
	void updateBoard(BoardUpdateDto boardUpdateDto) throws SQLException;
}
