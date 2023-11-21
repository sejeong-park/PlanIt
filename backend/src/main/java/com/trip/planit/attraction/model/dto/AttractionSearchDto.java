package com.trip.planit.attraction.model.dto;

import lombok.ToString;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionSearchDto {
	private int sidoCode;
	private int gugunCode;
	private String keyword;
	private int contentTypeId;
}
