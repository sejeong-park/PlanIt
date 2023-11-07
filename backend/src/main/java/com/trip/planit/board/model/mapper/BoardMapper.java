package com.trip.planit.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.board.model.dto.BoardDto;

@Mapper
public interface BoardMapper {
	void insertArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> selectAllArticles()throws SQLException;
}
