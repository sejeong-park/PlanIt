package com.trip.planit.plan.model.dto;

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
	private int detailId;
	private String title;
	private String content;
	private int sequence;
	private int attractionId;
}
