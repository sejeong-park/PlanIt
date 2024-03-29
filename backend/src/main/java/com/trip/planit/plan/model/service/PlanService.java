package com.trip.planit.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.plan.model.dto.*;

public interface PlanService {
	void writePlan(PlanRegistDto planRegistDto) throws SQLException;
	void writePlanDetail(List<PlanDetailDto> planDetailDtos) throws SQLException;
	List<PlanDetailResponseDto> findPlanDetail(String planKey) throws SQLException;
	PlanListDto findPlan(String planKey) throws SQLException;
	List<PlanListDto> findAllPlan() throws SQLException;
	List<PlanListDto> findMyPlans(String createUser) throws SQLException;
	void deletePlan(String planKey) throws SQLException;
	void deletePlanDetail(String planKey) throws SQLException;
	void updatePlanDetail(List<PlanUpdateDetailDto> updateDtos) throws SQLException; 
}
