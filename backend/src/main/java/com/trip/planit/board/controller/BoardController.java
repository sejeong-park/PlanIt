package com.trip.planit.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.trip.planit.board.model.dto.BoardListDto;
import com.trip.planit.board.model.dto.BoardListPageDto;
import com.trip.planit.board.model.dto.BoardRegistDto;
import com.trip.planit.board.model.dto.BoardUpdateDto;
import com.trip.planit.board.model.dto.FileInfoDto;
import com.trip.planit.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Api(tags = { "게시판 컨트롤러  API V1" })
@JsonAutoDetect
public class BoardController {

	private final BoardService boardService;

	@Value("${file.path}")
	private String uploadPath;

	/**
	 * 게시글 등록 API 넘겨 받는 값 : planKey, 게시글 title, create_user(유저
	 * 이름?),create_at,contents, hits 전체 게시글 리스트로 이동할 것이므로 게시글 전체 리스트를 반환
	 * 반환 : 게시글 생성시 자동으로 생성된 boardId
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@ApiOperation(value = "게시글을 등록합니다.", notes = "게시글을 등록합니다")
	@PostMapping(value = "/{planKey}")
	public ResponseEntity<?> regist(
			@PathVariable String planKey,
			@RequestPart BoardRegistDto boardRegistDto,
			@RequestPart MultipartFile file) throws IllegalStateException, IOException {
		try {
			boardRegistDto.setPlanKey(planKey);

			// System.out.println(file.getOriginalFilename());
			if (!file.isEmpty() && file != null) {
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = uploadPath + File.separator + today;
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = file.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					file.transferTo(new File(folder, saveFileName));
				}
				boardRegistDto.setFileInfoDto(fileInfoDto);
				// System.out.println(fileInfoDto);
			}

			int boardId = boardService.writeBoard(boardRegistDto);

			BoardListDto board = boardService.findBoard(boardId);

			System.out.println(board);
			return new ResponseEntity<BoardListDto>(board, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
//	@ApiOperation(value = "썸네일 등록.", notes = "썸네일 등록")
//	@PostMapping(value = "/{boardId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<?> registThumbnail(@PathVariable("boardId") String boardIdVal, @RequestPart MultipartFile file)
//			{
//		if (!file.isEmpty() && file != null) {
//			try {
//				int boardId = Integer.parseInt(boardIdVal);
//				BoardListDto board = boardService.findBoard(boardId); // 해당 게시글 상세 정보를 조회해서 userId를 가져온다.
//				
//				String today = new SimpleDateFormat("yyMMdd").format(new Date());
//				String saveFolder = uploadPath + File.separator + today;
//				File folder = new File(saveFolder);
//				if (!folder.exists())
//					folder.mkdirs();
//				FileInfoDto fileInfoDto = new FileInfoDto();
//				String originalFileName = file.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					file.transferTo(new File(folder, saveFileName));
//				}
//				
//				fileInfoDto.setUserId(board.getCreateUser());
//				fileInfoDto.setBoardId(boardId);
//				boardService.registFile(fileInfoDto);
//				
//				List<BoardListDto> boardListDtos = boardService.findAllBoard();
//				return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.CREATED);
//			} catch (Exception e) {
//				return exceptionHandling(e);
//			}
//		}else {
//			try {
//				List<BoardListDto> boardListDtos = boardService.findAllBoard();
//				return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.CREATED);
//			} catch (Exception e) {
//				return exceptionHandling(e);
//			}
//		}
//	}

	/**
	 * 전체 게시글 데이터를 반환합니다. 전체 게시글 리스트 반환 : List<boardListDto>
	 * 
	 * @return
	 */
	@ApiOperation(value = "전체 게시글을 반환합니다..", notes = "게시글 전체 리스트 목록 화면")
	@GetMapping("/")
	public ResponseEntity<?> list(
			@RequestParam @ApiParam(value="게시글을 얻기위한 부가정보", required = true) Map<String, String> map) {
		try {
//			System.out.println(map.toString());
			BoardListPageDto boardListPageDtos = boardService.findAllBoardForPage(map);
//			System.out.println(boardListPageDtos);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			
			return ResponseEntity.ok().headers(header).body(boardListPageDtos);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * 선택한 게시글을 조회합니다.
	 * 
	 * @return
	 */
	@ApiOperation(value = "상세 게시글 조회", notes = "게시글 상세 조회")
	@GetMapping("/{boardId}")
	public ResponseEntity<?> detail(@PathVariable("boardId") String boardIdVal) {
		try {
			int boardId = Integer.parseInt(boardIdVal);
			BoardListDto board = boardService.findBoard(boardId);
			return new ResponseEntity<BoardListDto>(board, HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
	}

	/**
	 * 게시글 삭제 기능 삭제 후 게시글 전체 리스트 반환
	 * 
	 * @return
	 */
	@ApiOperation(value = "단일 게시글 삭제", notes = "단일 게시글을 삭제합니다")
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> delete(@PathVariable("boardId") String boardId) {
		try {
			boardService.deleteBoard(boardId);
			List<BoardListDto> boardListDtos = boardService.findAllBoard();
			return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.CREATED);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * 게시글 수정 기능 게시글 수정 후 해당 단일 게시글 정보 반환
	 * 
	 * @return
	 */
	@ApiOperation(value = "단일 게시글 수정", notes = "단일 게시글을 수정합니다")
	@PatchMapping("/{boardId}")
	public ResponseEntity<?> update(@PathVariable("boardId") String boardIdVal,
			@RequestBody BoardUpdateDto boardUpdateDto) {
		try {
			int boardId = Integer.parseInt(boardIdVal);
			boardUpdateDto.setBoardId(boardId);
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
