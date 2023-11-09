package com.trip.planit.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanListDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;
import com.trip.planit.plan.model.dto.PlanUpdateDetailDto;
import com.trip.planit.plan.model.dto.PlanUpdateDto;

public interface PlanService {
	void writePlan(PlanRegistDto planRegistDto) throws SQLException;
	void writePlanDetail(List<PlanDetailDto> planDetailDtos) throws SQLException;
	List<PlanDetailDto> findPlanDetail(String planKey) throws SQLException;
	List<PlanListDto> findAllPlan() throws SQLException;
	void deletePlan(String planKey) throws SQLException;
	void deletePlanDetail(String planKey) throws SQLException;
	void updatePlan(PlanUpdateDto planUpdateDto) throws SQLException;
	void updateDetailPlan(List<PlanUpdateDetailDto> planUpdateDetailDtos) throws SQLException;
}
