package com.trip.planit.plan.controller;

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

import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.dto.PlanRegistTestDto;
import com.trip.planit.plan.model.service.PlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
@Api(tags = {"plan 컨트롤러  API V1"})
@Slf4j
@RequiredArgsConstructor
public class PlanController {

	private final PlanService planService;
	
	@ApiOperation(value="계획 등록", notes = "사용자가 입력한 계획 저장")
	@PostMapping("/plans")
	public void plans(@RequestBody PlanRegistTestDto planRegistTestDto) {
		try {
			String planKey = UUID.randomUUID().toString();
			System.out.println("planKey : " + planKey);
			planRegistTestDto.setPlanKey(planKey);
			planService.writePlanByNotUser(planRegistTestDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value="여행 계획 리스트", notes="저장된 계획 리스트 목록")
	@ApiResponses({@ApiResponse(code = 200, message = "계획 목록 OK!!"), @ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다"),
		@ApiResponse(code = 500, message="서버 에러")})
	@GetMapping("/plans")
	public ResponseEntity<?> plans(){
		try {
			List<PlanRegistTestDto> list =  planService.findAllPlan();
			System.out.println("list : " + list.toString());
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanRegistTestDto>>(list, HttpStatus.OK);
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
