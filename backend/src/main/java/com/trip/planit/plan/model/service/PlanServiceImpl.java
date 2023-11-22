package com.trip.planit.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanListDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;
import com.trip.planit.plan.model.dto.PlanUpdateDetailDto;
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
	public void writePlanDetail(List<PlanDetailDto> planDetailDtos) throws SQLException {
		planMapper.insertPlanDetail(planDetailDtos);
	}

	@Override
	public List<PlanDetailDto> findPlanDetail(String planKey) throws SQLException {
		return planMapper.selectPlanDetail(planKey);
	}

	@Override
	public PlanListDto findPlan(String planKey) throws SQLException {
		return planMapper.selectPlan(planKey);
	}

	@Override
	public void deletePlan(String planKey) throws SQLException {
		planMapper.deletePlan(planKey);
	}

	@Override
	public void deletePlanDetail(String planKey) throws SQLException {
		planMapper.deletePlanDetail(planKey);
	}

	@Override
	public void updatePlanDetail(List<PlanUpdateDetailDto> updateDtos) throws SQLException {
		planMapper.updatePlanDetail(updateDtos);
	}

	@Override
	public List<PlanListDto> findMyPlans(String createUser) throws SQLException {
		return planMapper.selectMyPlans(createUser);
	}
}
