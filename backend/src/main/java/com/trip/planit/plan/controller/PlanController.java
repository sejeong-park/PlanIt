package com.trip.planit.plan.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanListDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;
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
	@ApiOperation(value="계획 등록", notes = "비회원 사용자가 입력한 계획 저장")
	@PostMapping("/plans")
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
	/**
	 * [
  {
    "attractionId": 1,
    "content": "string1",
    "planDate": "2023-11-09",
    "planKey": "string",
    "sequence": 1,
    "title": "string1"
  },
{
    "attractionId": 1,
    "content": "string2",
    "planDate": "2023-11-09",
    "planKey": "string",
    "sequence": 2,
    "title": "string2"
  },
{
    "attractionId": 1,
    "content": "string2",
    "planDate": "2023-11-10",
    "planKey": "string",
    "sequence": 2,
    "title": "string2"
  },
{
    "attractionId": 1,
    "content": "string2",
    "planDate": "2023-11-10",
    "planKey": "string",
    "sequence": 2,
    "title": "string2"
  }
]
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
			List<PlanListDto> list = planService.findAllPlan();
			return new ResponseEntity<List<PlanListDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<PlanDto>()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value="여행 계획 리스트", notes="저장된 계획 리스트 목록")
	@ApiResponses({@ApiResponse(code = 200, message = "계획 목록 OK!!"), @ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다"),
		@ApiResponse(code = 500, message="서버 에러")})
	@GetMapping("/plans")
	public ResponseEntity<?> plans(){
		try {
			List<PlanListDto> list =  planService.findAllPlan();
			System.out.println("list : " + list.toString());
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanListDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
