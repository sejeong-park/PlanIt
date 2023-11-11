package com.trip.planit.board.model.dto;

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
public class FileInfoDto {
	private String saveFolder;
	private String originalFile;
	private String saveFile;
	private String userId;
	private int boardId;
}
