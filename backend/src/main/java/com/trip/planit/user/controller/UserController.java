package com.trip.planit.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.planit.user.model.dto.UserDto;
import com.trip.planit.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Api(tags = {"어드민 컨트롤러  API V1"})
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	
	/**
	 * ApiOperation ?
	 * ApiResponses ?
	 * ResponseEntity ?  
	 * @return
	 */
	@ApiOperation(value = "회원목록", notes = "회원의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/list")
	public ResponseEntity<?> userList() {
		log.debug("userList call");
		try {
			List<UserDto> list = userService.listUser();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<userDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
