package com.trip.planit.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.dto.PlanRegistTestDto;

public interface PlanService {
	void writePlanByNotUser(PlanRegistTestDto planRegistTestDto) throws SQLException;
	List<PlanRegistTestDto> findAllPlan() throws SQLException;
}
