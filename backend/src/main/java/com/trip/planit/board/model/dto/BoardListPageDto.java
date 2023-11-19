package com.trip.planit.board.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardListPageDto {
	private List<BoardListDto> articles; 
	private int currentPage;
	private int totalPageCount;
}
