package com.trip.planit.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
//				String saveFolder = uploadPath + File.separator + today;
				File folder = new File(uploadPath);
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


	/**
	 * 전체 게시글 데이터를 반환합니다. 전체 게시글 리스트 반환 : List<boardListDto>
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "전체 게시글을 반환합니다..", notes = "게시글 전체 리스트 목록 화면")
	@GetMapping("/")
	public ResponseEntity<?> list(
	        @RequestParam @ApiParam(value="게시글을 얻기위한 부가정보", required = true) Map<String, String> map) throws Exception {
	    try {
	        String path ="C:\\board\\upload\\";
	        String folder = "";
	        BoardListPageDto boardListPageDtos = boardService.findAllBoardForPage(map);

	        List<BoardListDto> boardListDtos = new ArrayList<BoardListDto>(); 
	        for (BoardListDto boardListDto : boardListPageDtos.getArticles()) {
	            int boardId = boardListDto.getBoardId();
	            String fileName = boardService.findFileName(boardId);
	            // Check if fileName is null, set it to an empty string
	            Path filePath = Paths.get(path + folder + fileName);
	            // 이미지 파일을 Base64로 인코딩하여 문자열로 변환
	            // BoardListDto에 Base64 인코딩된 이미지 데이터 추가
	            // 등록된 썸네일이 없다면 null을 반환해준다.
	            if(fileName != null) {
	            	String base64Image = Base64.getEncoder().encodeToString(Files.readAllBytes(filePath));
	            	boardListDto.setBase64Image(base64Image);	
	            }
	            boardListDtos.add(boardListDto);
	        }

	        boardListPageDtos.setArticles(boardListDtos);
	        return ResponseEntity.ok(boardListPageDtos);
	    } catch (SQLException | IOException e) {
	        return exceptionHandling(e);
	    }
	}
	

	/**
	 * 선택한 게시글을 조회합니다.
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
	 * @return
	 */
	@ApiOperation(value = "단일 게시글 삭제", notes = "단일 게시글을 삭제합니다")
	@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.DELETE})
	@DeleteMapping("/{boardId}")
	public void delete(@PathVariable("boardId") String boardId) {
		try {
			boardService.deleteBoard(boardId);
//			List<BoardListDto> boardListDtos = boardService.findAllBoard();
//			return new ResponseEntity<List<BoardListDto>>(boardListDtos, HttpStatus.CREATED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시글 수정 기능 게시글 수정 후 해당 단일 게시글 정보 반환
	 * @return
	 */
	@ApiOperation(value = "단일 게시글 수정", notes = "단일 게시글을 수정합니다")
	@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.PATCH})
	@PatchMapping("/{boardId}")
	public ResponseEntity<?> update(@PathVariable("boardId") String boardIdVal,
			@RequestBody BoardUpdateDto boardUpdateDto) {
		try {
			int boardId = Integer.parseInt(boardIdVal);
			boardUpdateDto.setBoardId(boardId);
			boardService.updateBoard(boardUpdateDto);
			BoardListDto board = boardService.findBoard(boardId);
//			System.out.println("게시글 업데이트 호출!");
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
	
	@ApiOperation(value="마이페이지, 나의 게시글")
	@GetMapping("/mypage")
	public ResponseEntity<?> boardMypage(@RequestParam("createUser") String createUser) throws Exception{
		
		try {
			List<BoardListDto> answerBoards = new ArrayList<BoardListDto>();
			String path ="C:\\board\\upload\\";
	        String folder = "";
			List<BoardListDto> boardListDtos = boardService.findMyBoard(createUser);
			for (BoardListDto boardListDto : boardListDtos) {
				int boardId = boardListDto.getBoardId();
				String fileName = boardService.findFileName(boardId);
	            // Check if fileName is null, set it to an empty string
	            Path filePath = Paths.get(path + folder + fileName);
	            // 이미지 파일을 Base64로 인코딩하여 문자열로 변환
	            // BoardListDto에 Base64 인코딩된 이미지 데이터 추가
	            // 등록된 썸네일이 없다면 null을 반환해준다.
	            if(fileName != null) {
	            	String base64Image = Base64.getEncoder().encodeToString(Files.readAllBytes(filePath));
	            	boardListDto.setBase64Image(base64Image);	
	            }
	            answerBoards.add(boardListDto);
			}
	        return ResponseEntity.ok(answerBoards);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
}
