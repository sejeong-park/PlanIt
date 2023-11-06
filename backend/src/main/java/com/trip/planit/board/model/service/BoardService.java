package com.trip.planit.board.model.service;

import java.sql.SQLException;

import com.trip.planit.board.model.dto.BoardDto;

public interface BoardService {
	void writeArticle(BoardDto boardDto) throws SQLException;
}
