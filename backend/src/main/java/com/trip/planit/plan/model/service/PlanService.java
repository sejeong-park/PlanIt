package com.trip.planit.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;

public interface PlanService {
	void writePlan(PlanRegistDto planRegistDto) throws SQLException;
	void writePlanDetail(List<PlanDetailDto> planDetailDtos) throws SQLException;
	List<PlanRegistDto> findAllPlan() throws SQLException;
}
