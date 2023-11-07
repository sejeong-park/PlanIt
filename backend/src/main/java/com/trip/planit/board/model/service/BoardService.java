package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.board.model.dto.BoardDto;

public interface BoardService {
	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> findAllArticles()throws SQLException;

}
