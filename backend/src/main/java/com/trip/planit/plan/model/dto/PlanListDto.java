package com.trip.planit.plan.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlanListDto {
	private String planKey;
	private String createUser;
	private Date startDate;
	private Date endDate;
	private Timestamp createAt;
	private Timestamp updateAt;
	private String title;
}
