package com.trip.planit.plan.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.mapper.PlanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

	private PlanMapper planMapper;

	@Override
	public void writePlanByNotUser(PlanDto planDto) throws SQLException {
		planMapper.insertPlanByNotUser(planDto);
	}
}
