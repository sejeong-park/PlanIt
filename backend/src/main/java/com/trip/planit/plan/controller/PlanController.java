package com.trip.planit.plan.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanListDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;
import com.trip.planit.plan.model.dto.PlanUpdateDetailDto;
import com.trip.planit.plan.model.service.PlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plans")
@CrossOrigin("*")
@Api(tags = {"plan 컨트롤러  API V1"})
@Slf4j
@RequiredArgsConstructor
@JsonAutoDetect
public class PlanController {

	private final PlanService planService;
	/**
	 * {
  "anonymousPassword": "string",
  "createAt": "2023-11-07T17:30:50",
  "createUser" : "userId", // 비회원이라면 프론트에서 입력 form을 제거해주면 된다.
  "endDate": "2023-11-07",
  "planKey": "string",
  "startDate": "2023-11-07",
}
	 * @param planRegistTestDto
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value="계획 등록", notes = "여행 계획 저장")
	@PostMapping("/")
	public ResponseEntity<String> plans(@RequestBody PlanRegistDto planRegistDto) throws JsonProcessingException {
		try {
			String planKey = UUID.randomUUID().toString();
			
			planRegistDto.setPlanKey(planKey);
			planService.writePlan(planRegistDto);
			
			 // ObjectMapper를 사용하여 JSON 문자열로 직렬화
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonPlanKey = objectMapper.writeValueAsString(planKey);
			
			return new ResponseEntity<String>(jsonPlanKey, HttpStatus.CREATED);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	/*
	 * @param planDetailDtos
	 * @param planKey
	 */
	
	@ApiOperation(value="계획 디테일 등록", notes = "비회원 사용자가 입력한 계획 세부 일정 저장")
	@PostMapping("/{planKey}")
	public ResponseEntity<?> planDetail(@RequestBody List<PlanDetailDto> planDetailDtos,
			@PathVariable("planKey") String planKey) {
		
		try {
			for(PlanDetailDto planDetailDto : planDetailDtos) {
				planDetailDto.setPlanKey(planKey);
			}
			
			planService.writePlanDetail(planDetailDtos);
			List<PlanDetailDto> planDetailDtoList = planService.findPlanDetail(planKey);
			return new ResponseEntity<List<PlanDetailDto>>(planDetailDtoList, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value= "전체 여행 일정", notes="단일 여행 계획들을 가져온다.")
	@ApiResponses({@ApiResponse(code=200, message="단일 계획 목록 가져오기 OK!"),
			@ApiResponse(code=204, message="요청에 대해서 보내줄 수 있는 콘텐츠가 없습니다"),
			@ApiResponse(code=404, message="서버로 요청받은 페이지를 찾을 수 없습니다."),
			@ApiResponse(code=500, message="서버에 문제가 발생했습니다.")})
	@GetMapping("/{planKey}")
	public ResponseEntity<?> planInfo(@PathVariable String planKey) {
		try {
			PlanListDto planListDto = planService.findPlan(planKey);
			return new ResponseEntity<PlanListDto>(planListDto, HttpStatus.OK);
		}catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * 여행 계획 상세 보기
	 * 단일 여행 계획 수정 버튼을 눌렀을 때도 호출된다.
	 * @param planKey
	 * @return planKey에 해당하는 plan_detail 값들을 출력해준다.
	 */
	@ApiOperation(value="단일 여행 계획들 가져오기", notes="단일 여행 계획들을 가져온다.")
	@ApiResponses({@ApiResponse(code=200, message="단일 계획 목록 가져오기 OK!"),
		@ApiResponse(code=204, message="요청에 대해서 보내줄 수 있는 콘텐츠가 없습니다"),
		@ApiResponse(code=404, message="서버로 요청받은 페이지를 찾을 수 없습니다."),
		@ApiResponse(code=500, message="서버에 문제가 발생했습니다.")})
	@GetMapping("/list/{planKey}")
	public ResponseEntity<?> planDetail(@PathVariable String planKey){
		try {
			List<PlanDetailDto> planDetailDtoList = planService.findPlanDetail(planKey);
			
			return new ResponseEntity<List<PlanDetailDto>>(planDetailDtoList, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * 여행 계획 삭제하기
	 * 전달 받는 값 : planKey
	 * response : 삭제하고 난 뒤에 바로 전체 리스트로 이동하기 위해 클라이언트에 plan 전체 list 값을 넘겨준다
	 */
	@ApiOperation(value="단일 여행 계획 삭제하기", notes="단일 여행 계획을 삭제한다.")
	@DeleteMapping("/{planKey}")
	public ResponseEntity<?> planDelete(@PathVariable("planKey") String planKey){
		try {
			// 외래키 조건으로 인해 detail 값을 먼저 삭제한다.
			planService.deletePlanDetail(planKey);
			planService.deletePlan(planKey);
			
			List<PlanListDto> list = planService.findAllPlan();
			return new ResponseEntity<List<PlanListDto>>(list, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * 클라이언트로 넘겨 받는 데이터 : List<planKey, detailId,title, content, sequence, attraction_id>
	 * 수정된 후에 다시 상세 페이지로 이동할 것이므로 List<PlanDetailDto> 반환 
	 * @param planKey
	 * @return
	 */
	@ApiOperation(value="단일 여행 계획 수정하기", notes="단일 여행 계획을 수정한다.")
	@PatchMapping("/{planKey}")
	public ResponseEntity<?> planUpdate(@PathVariable("planKey") String planKey, @RequestBody List<PlanUpdateDetailDto> updateDtos){
		try {
			planService.updatePlanDetail(updateDtos);
			List<PlanDetailDto> planDetailDtoList = planService.findPlanDetail(planKey);
			return new ResponseEntity<List<PlanDetailDto>>(planDetailDtoList, HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
	}

	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value="여행 계획 전체 리스트", notes="저장된 계획 리스트 목록")
	@ApiResponses({@ApiResponse(code = 200, message = "계획 목록 OK!!"), @ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다"),
		@ApiResponse(code = 500, message="서버 에러")})
	@GetMapping("/")
	public ResponseEntity<?> plans(){
		try {
			List<PlanListDto> list =  planService.findAllPlan();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanListDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
}
