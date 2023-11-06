package com.trip.planit.board.controller;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.planit.board.model.dto.BoardDto;
import com.trip.planit.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
