package com.trip.planit.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanListDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;
import com.trip.planit.plan.model.mapper.PlanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

	private final PlanMapper planMapper;

	@Override
	public void writePlan(PlanRegistDto planRegistDto) throws SQLException {
		planMapper.insertPlan(planRegistDto);
	}
	
	@Override
	public List<PlanListDto> findAllPlan() throws SQLException {
		return planMapper.selectAllPlan();
	}

	@Override
	public void writePlanDetail(List<PlanDetailDto> planDetailDtos) throws SQLException {
		planMapper.insertPlanDetail(planDetailDtos);
	}
}
