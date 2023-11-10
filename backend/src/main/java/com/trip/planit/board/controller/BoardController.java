package com.trip.planit.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Api(tags = { "게시판 컨트롤러  API V1" })
@JsonAutoDetect
public class BoardController {

	private final BoardService boardService;

	/**
	 * 게시글 등록 API 넘겨 받는 값 : planKey, 게시글 title, create_user(유저 이름?),create_at,contents, hits
	 * 전체 게시글 리스트로 이동할 것이므로 게시글 전체 리스트를 반환
	 * @return
	 */
	@ApiOperation(value = "게시글을 등록합니다.",notes = "게시글을 등록합니다")
	@PostMapping("/{planKey}")
	public ResponseEntity<?> regist(@PathVariable String planKey, @RequestBody BoardRegistDto boardRegistDto) {
		try {
			boardRegistDto.setPlanKey(planKey);
			boardService.writeBoard(boardRegistDto);
			List<BoardListDto> boardListDtos = boardService.findAllBoard();
			return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * 전체 게시글 데이터를 반환합니다.
	 * 전체 게시글 리스트
	 * 반환 : List<boardListDto>
	 * @return
	 */
	@ApiOperation(value = "전체 게시글을 반환합니다..",notes = "게시글 전체 리스트 목록 화면")
	@GetMapping("/")
	public ResponseEntity<?> list(){
		try {
			List<BoardListDto> boardListDtos = boardService.findAllBoard();
			return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.CREATED);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * 선택한 게시글을 조회합니다.
	 * @return
	 */
	@ApiOperation(value = "상세 게시글 조회",notes = "게시글 상세 조회")
	@GetMapping("/{boardId}")
	public ResponseEntity<?> detail(@PathVariable("boardId") String boardId){
		try {
			BoardListDto board = boardService.findBoard(boardId);
			return new ResponseEntity<BoardListDto>(board, HttpStatus.OK); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
	}
	
	/**
	 * 게시글 삭제 기능
	 * 삭제 후 게시글 전체 리스트 반환
	 * @return
	 */
	@ApiOperation(value = "단일 게시글 삭제",notes = "단일 게시글을 삭제합니다")
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> delete(@PathVariable("boardId") String boardId){
		try {
			boardService.deleteBoard(boardId);
			List<BoardListDto> boardListDtos = boardService.findAllBoard();
			return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.CREATED);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * 게시글 수정 기능
	 * 게시글 수정 후 해당 단일 게시글 정보 반환
	 * @return
	 */
	@ApiOperation(value = "단일 게시글 수정",notes = "단일 게시글을 수정합니다")
	@PatchMapping("/{boardId}")
	public ResponseEntity<?> update(@PathVariable("boardId") String boardId, @RequestBody BoardUpdateDto boardUpdateDto){
		try {
			int boardIdInt = Integer.parseInt(boardId);
			boardUpdateDto.setBoardId(boardIdInt);
			boardService.updateBoard(boardUpdateDto);
			BoardListDto board = boardService.findBoard(boardId);
			return new ResponseEntity<BoardListDto>(board, HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
	}
	
	

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
