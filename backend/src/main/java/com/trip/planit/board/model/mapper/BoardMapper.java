package com.trip.planit.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.dto.FileInfoDto;

@Mapper
public interface BoardMapper {
	void insertBoard(BoardRegistDto boardRegistDto) throws SQLException;
	int selectBoardId() throws SQLException;
	void insertFile(FileInfoDto fileInfoDto) throws Exception;
	List<BoardListDto> selectAllBoard() throws SQLException;
	BoardListDto selectBoard(int boardId) throws SQLException;
	void deleteBoard(String boardId) throws SQLException;
	void updateBoard(BoardUpdateDto boardUpdateDto) throws SQLException;
}
