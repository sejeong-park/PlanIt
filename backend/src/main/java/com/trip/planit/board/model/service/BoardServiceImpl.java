package com.trip.planit.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.planit.board.model.dto.BoardListDto;
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
	public void writeBoard(BoardRegistDto boardRegistDto) throws SQLException {
//		System.out.println("글 입력 전 dto : " + boardRegistDto);
		boardMapper.insertBoard(boardRegistDto);
		System.out.println("글 입력 후 dto : " + boardRegistDto);
		FileInfoDto fileInfo = boardRegistDto.getFileInfoDto();
		if(fileInfo != null) {
//			System.out.println(boardRegistDto.getFileInfoDto());
			try {
				FileInfoDto fileInfoDto = boardRegistDto.getFileInfoDto();
				fileInfoDto.setBoardId(boardRegistDto.getBoardId());
				boardMapper.insertFile(boardRegistDto.getFileInfoDto());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<BoardListDto> findAllBoard() throws SQLException {
		return boardMapper.selectAllBoard();
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
}
