package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardListPageDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.dto.FileInfoDto;
import com.trip.planit.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper boardMapper;

	
	@Override
	@Transactional
	public int writeBoard(BoardRegistDto boardRegistDto) throws SQLException {
		boardMapper.insertBoard(boardRegistDto);
		return boardMapper.selectBoardId();
	}

	@Override
	public BoardListPageDto findAllBoardForPage(Map<String, String> map) throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "6" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);
		System.out.println(start + " " + sizePerPage);
		
		List<BoardListDto> list = boardMapper.selectAllBoardForPage(param);
		
		int totalArticleCount = boardMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1)/ sizePerPage+1;
		
		BoardListPageDto boardListPageDto = new BoardListPageDto();
		boardListPageDto.setArticles(list);
		boardListPageDto.setCurrentPage(currentPage);
		boardListPageDto.setTotalPageCount(totalPageCount);
		
		return boardListPageDto;
	}

	@Override
	public BoardListDto findBoard(int boardId) throws SQLException {
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

	@Override
	public void registFile(FileInfoDto fileInfoDto) throws Exception {
		boardMapper.insertFile(fileInfoDto);
	}

	@Override
	public List<BoardListDto> findAllBoard() throws SQLException {
		return boardMapper.selectAllBoard();
	}
}
