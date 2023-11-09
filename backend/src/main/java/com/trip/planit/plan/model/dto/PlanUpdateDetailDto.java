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
public class PlanUpdateDetailDto {
	private String planKey;
	private String title;
	private String content;
	private Date planDate;
	private int sequence;
	private int attractionId;
}
