package com.trip.planit.board.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
	private String userName;
	private String contents;
	private int boardId;
	private String createAt;
}
