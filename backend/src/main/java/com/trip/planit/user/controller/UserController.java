package com.trip.planit.user.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip.planit.user.model.dto.User;
import com.trip.planit.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = {"어드민 컨트롤러  API V1"})
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "로그인", notes = "로그인을 합니다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User requestUser) throws Exception{

		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.loginUser(requestUser); // 로그인
		if(user != null) {
			System.out.println("로그인 성공!!");
		}else {
			System.out.println("로그인 실패!!");
			return new ResponseEntity<Map<String,Object>>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "로그아웃", notes = "로그아웃을 합니다.")
	@GetMapping("/logout")
	public ResponseEntity logout() throws Exception{
		// 토큰 기반 로그아웃 구현 필요
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "회원가입", notes = "회원가입을 합니다.")
	@PostMapping("/regist")
	public ResponseEntity regist(@RequestBody User requestUser) throws Exception{
		userService.registUser(requestUser);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "회원탈퇴", notes = "회원탈퇴를 합니다.")
	@DeleteMapping("/{userId}")
	public ResponseEntity withdraw(@PathVariable String userId) throws Exception{
		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "마이페이지", notes = "마이페이지를 확인합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<User> mypage(@PathVariable String userId) throws Exception{
		User user = userService.findByUserId(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "회원정보를 수정합니다.",notes = "마이페이지에서 회원정보를 수정합니다.")
	@PutMapping("/{userId}")
	public ResponseEntity<?> modify(@RequestBody User requestUser) throws Exception{
		// mypage 수정에 있어, 동적 쿼리를 활용하여 일부 데이터만 수정하는 것이 좋다고 한다. -> 기본 구현 다 하고 수정할 예정
		userService.modifyUser(requestUser);
		return ResponseEntity.ok().build();
	}



//	private ResponseEntity<String> exceptionHandling(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
