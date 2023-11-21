package com.trip.planit.attraction.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionSearchDto {
	private Integer sidoCode;
	private Integer gugunCode;
	private String keyword;
	private Integer contentTypeId;
}
