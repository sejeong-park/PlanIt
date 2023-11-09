package com.trip.planit.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.plan.model.dto.PlanDetailDto;
import com.trip.planit.plan.model.dto.PlanListDto;
import com.trip.planit.plan.model.dto.PlanRegistDto;

@Mapper
public interface PlanMapper {
	void insertPlan(PlanRegistDto planRegistDto) throws SQLException;
	void insertPlanDetail(List<PlanDetailDto> planDetailDtos) throws SQLException;
	List<PlanListDto> selectAllPlan() throws SQLException;
}
