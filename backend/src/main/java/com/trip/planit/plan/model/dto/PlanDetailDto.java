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
public class PlanDetailDto {
	private Date planDate;
	private String planKey;
	private int sequence;
	private String title;
	private String content;
	private int attractionId;
	
}
