package com.trip.planit.board.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.planit.board.model.dto.BoardDto;
import com.trip.planit.board.model.service.BoardService;
import com.trip.planit.plan.model.dto.PlanRegistDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
@Api(tags = {"board 컨트롤러  API V1"})
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@ApiOperation(value="게시글 등록", notes = "사용자가 입력한 게시글 저장")
	@PostMapping("/articles")
	public void registArticle(@RequestBody BoardDto boardDto) {
		try {
			boardService.writeArticle(boardDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value="게시판 리스트", notes="저장된 게시판 목록")
	@ApiResponses({@ApiResponse(code = 200, message = "게시판 목록 OK!!"), @ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다"),
		@ApiResponse(code = 500, message="서버 에러")})
	@GetMapping("/articles")
	public ResponseEntity<?> plans(){
		try {
			List<BoardDto> list = boardService.findAllArticles();
			System.out.println("list : " + list.toString());
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
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
