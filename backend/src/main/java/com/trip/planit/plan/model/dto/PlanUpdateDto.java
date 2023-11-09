package com.trip.planit.plan.model.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlanUpdateDto {
	private String planKey;
	private Date startDate;
	private Date endDate;
}
