package com.trip.planit.plan.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PlanRegistTestDto {
	private String planKey;
	private String anonymousPassword;
	private Date startDate;
	private Date endDate;
}
