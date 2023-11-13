package com.trip.planit.user.controller;

import java.util.HashMap;
import java.util.Map;

import com.trip.planit.annotation.AuthRequired;
import com.trip.planit.user.model.dto.Auth;
import com.trip.planit.user.model.service.AuthService;
import com.trip.planit.util.JWTUtil;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip.planit.user.model.dto.User;
import com.trip.planit.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = {"User 컨트롤러"})
public class UserController {

	private final UserService userService;
	private final JWTUtil jwtUtil;
	private final AuthService authService;

	@Value("${jwt.refreshtoken.expiretime}")
	private Integer refreshTokenExpireTime; // refresh 토큰 만료 기간


	// 로그인
	@ApiOperation(value = "로그인", notes = "로그인 성공 시 JWT를 반환합니다.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "로그인 성공"),
			@ApiResponse(code = 401, message = "로그인 실패")
	})
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User requestUser, HttpServletResponse response) throws Exception{

		Map<String, Object> result = new HashMap<String, Object>();
		User validUser = userService.loginUser(requestUser); // 로그인

		// 일치하는 사용자가 없을 경우
		if (validUser == null ){
			result.put("message", "아이디 또는 패스워드를 확인해주세요.");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.UNAUTHORIZED);
		}


		// AccessToken과 RefreshToken 발급
		String accessToken = jwtUtil.createAccessToken(validUser.getUserId()); // id값으로 토큰 생성
		String refreshToken = jwtUtil.createRefreshToken(validUser.getUserId());

		// RefreshToken은 HttpOnly로 Cookie 발급
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setMaxAge(refreshTokenExpireTime);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		// 이미 발급 받은 refreshToken이 있는 지 확인
		Auth auth = authService.getRefreshToken(validUser.getUserId());

		if (auth != null){
			auth.setRefreshToken(refreshToken);
			authService.updateRefreshToken(auth);
		} else {
			// 발급 받은 refresh token db에 저장
			authService.setRefreshToken(new Auth(validUser.getUserId(), refreshToken));
		}

		// AccessToken은 json으로 전달
		result.put("accessToken", accessToken);
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.CREATED);
	}



	// 로그아웃
	@ApiOperation(value = "로그아웃", notes = "로그아웃을 합니다. (해당 사용자의 refresh token을 삭제합니다.)")
	@ApiResponses({
			@ApiResponse(code = 200, message = "로그아웃 성공"),
			@ApiResponse(code = 401, message = "로그아웃 실패")
	})
	@DeleteMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) throws Exception{

		Map<String, Object> result = new HashMap<String, Object>();

		// 헤더에 있는 인증값 토큰에서 아이디 가져오기
		String userId = jwtUtil.getUserId(request.getHeader("Authorization"));

		// Cookie에 있는 Refresh Token 초기화
		Cookie cookie = new Cookie("refreshToken", "");
		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		authService.updateRefreshToken(new Auth("userId","")); // 리프레시 토큰 공백으로 업데이트

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}


	// 회원가입
	@ApiOperation(value = "회원가입", notes = "회원가입을 합니다.")
	@PostMapping("/regist")
	public ResponseEntity regist(@RequestBody User requestUser) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();

		userService.registUser(requestUser);
		result.put("message", "회원가입이 완료되었습니다.");
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
	}



	// 회원 탈퇴
	@ApiOperation(value = "회원탈퇴", notes = "회원탈퇴를 합니다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "사용자 정보 삭제 성공"),
			@ApiResponse(code = 403, message = "권한 없음"),
			@ApiResponse(code = 404, message = "사용자 정보 없음")
	})
	@AuthRequired // 인증이 필요
	@DeleteMapping("/{userId}")
	public ResponseEntity withdraw(@PathVariable String userId) throws Exception {

		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}



	// 마이페이지
	@ApiOperation(value = "마이페이지", notes = "마이페이지를 확인합니다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "사용자 정보 반환 성공"),
			@ApiResponse(code = 403, message = "권한 없음"),
			@ApiResponse(code = 404, message = "사용자 정보 없음")
	})
	@AuthRequired // 인증 필요
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> mypage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();

		// Authorization에 포함된 accessToken의 userId 가져온다.
		String userId = jwtUtil.getUserId(request.getHeader("Authorization"));
		Cookie[] cookieList = request.getCookies();
		String requestRefreshToken = ""; // 리프레시 토큰

		for (Cookie cookie : cookieList){
			if (cookie.getName().equals("refreshToken")){
				requestRefreshToken = cookie.getValue();
			}
		}

		// 요청으로 들어온 refreshToken이 database의 refreshToken과 일치하는 지 확인
		String dbRefreshToken = authService.getRefreshToken(userId).getRefreshToken();

		if (! requestRefreshToken.equals(dbRefreshToken)){
			// 쿠키 발급

			// RefreshToken은 HttpOnly Cookie로 발급
			Cookie cookie = new Cookie("refreshToken", "");
			cookie.setMaxAge(0);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);

			result.put("message", "로그인이 필요합니다.");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.FORBIDDEN);
		}

		// 해당 userId의 정보 획득
		User user = userService.findByUserId(userId);
		user.setUserPassword(""); // 비밀번호 정보는 전송하지 말아야 한다.
		result.put("userInfo", user);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}


	// 회원정보 수정
	@ApiOperation(value = "회원정보를 수정합니다.",notes = "마이페이지에서 회원정보를 수정합니다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "사용자 정보 수정 성공"),
			@ApiResponse(code = 403, message = "권한 없음"),
			@ApiResponse(code = 404, message = "사용자 정보 없음")
	})
	@AuthRequired // 인증 필요
	@PutMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody Map<String, String> requestMap, HttpServletRequest request) throws Exception{

		Map<String, Object> result = new HashMap<String, Object>();

		// accessToken에 저장된 userId 획득
		String accessTokenUserId = jwtUtil.getUserId(request.getHeader(("Authorization")));
		String requestUserId = requestMap.get("id");

		boolean isUpdated = userService.modifyUser(requestMap);

		// 검증
		if (!isUpdated){
			result.put("message", "일치하는 사용자가 없습니다.");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}

		result.put("message", "회원정보가 수정되었습니다. 다시 로그인해주세요.");
		authService.updateRefreshToken(new Auth(accessTokenUserId, ""));
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}
