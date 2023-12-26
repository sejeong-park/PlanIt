package com.trip.planit.board.model.dto;
/**
 * planKey, 게시글 title, create_user(유저 이름?),create_at, contents, hits 
 * @author SSAFY
 */

import java.sql.Timestamp;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardRegistDto {
	private int boardId;
	private String planKey;
	private String title;
	private String createUser;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/seoul")
	private Timestamp createAt;
	private String contents;
	private int hits;
	private FileInfoDto fileInfoDto;
}
