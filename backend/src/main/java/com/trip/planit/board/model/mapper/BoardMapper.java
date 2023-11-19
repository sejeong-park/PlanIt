package com.trip.planit.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.dto.FileInfoDto;

@Mapper
public interface BoardMapper {
	int insertBoard(BoardRegistDto boardRegistDto) throws SQLException;
	void insertFile(FileInfoDto fileInfoDto) throws Exception;
	int selectBoardId() throws SQLException;
	List<BoardListDto> selectAllBoardForPage(Map<String, Object> param) throws SQLException;
	List<BoardListDto> selectAllBoard() throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardListDto selectBoard(int boardId) throws SQLException;
	void deleteBoard(String boardId) throws SQLException;
	void updateBoard(BoardUpdateDto boardUpdateDto) throws SQLException;
}
