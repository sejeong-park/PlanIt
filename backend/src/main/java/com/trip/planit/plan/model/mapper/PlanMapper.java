package com.trip.planit.plan.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.plan.model.dto.PlanDto;

@Mapper
public interface PlanMapper {
	void insertPlanByNotUser(PlanDto planDto) throws SQLException;
}
