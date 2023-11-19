package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardListPageDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.dto.FileInfoDto;

public interface BoardService {
	int writeBoard(BoardRegistDto boardRegistDto) throws SQLException;
<<<<<<< HEAD
=======
	void registFile(FileInfoDto fileInfoDto) throws Exception;
	BoardListPageDto findAllBoardForPage(Map<String, String> map) throws SQLException;
>>>>>>> origin/feature#10
	List<BoardListDto> findAllBoard() throws SQLException;
	BoardListDto findBoard(int boardId) throws SQLException;
	void deleteBoard(String boardId) throws SQLException;
	void updateBoard(BoardUpdateDto boardUpdateDto) throws SQLException;
	void registFile(FileInfoDto fileInfoDto) throws Exception;
}
