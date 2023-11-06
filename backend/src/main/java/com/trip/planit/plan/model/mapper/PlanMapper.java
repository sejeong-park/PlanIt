package com.trip.planit.plan.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.plan.model.dto.PlanDto;
import com.trip.planit.plan.model.dto.PlanRegistTestDto;

@Mapper
public interface PlanMapper {
	void insertPlanByNotUser(PlanRegistTestDto planRegistTestDto) throws SQLException;
}
