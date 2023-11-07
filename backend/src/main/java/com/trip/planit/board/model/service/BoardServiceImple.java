package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.planit.board.model.dto.BoardDto;
import com.trip.planit.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

	private final BoardMapper boardMapper;
	
	@Override
	public void writeArticle(BoardDto boardDto) throws SQLException {
		boardMapper.insertArticle(boardDto);
	}

	@Override
	public List<BoardDto> findAllArticles() throws SQLException {
		return boardMapper.selectAllArticles();
	}
}
