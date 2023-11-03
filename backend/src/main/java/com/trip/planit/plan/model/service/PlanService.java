package com.trip.planit.plan.model.service;

import java.sql.SQLException;

import com.trip.planit.plan.model.dto.PlanDto;

public interface PlanService {
	void writePlanByNotUser(PlanDto planDto) throws SQLException;
}
