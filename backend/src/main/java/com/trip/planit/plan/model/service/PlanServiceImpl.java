package com.trip.planit.plan.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.dto.PlanRegistTestDto;
import com.trip.planit.plan.model.mapper.PlanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

	private final PlanMapper planMapper;

	@Override
	public void writePlanByNotUser(PlanRegistTestDto planRegistTestDto) throws SQLException {
		planMapper.insertPlanByNotUser(planRegistTestDto);
	}
}
