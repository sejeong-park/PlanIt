package com.trip.planit.plan.controller;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.dto.PlanRegistTestDto;
import com.trip.planit.plan.model.service.PlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
