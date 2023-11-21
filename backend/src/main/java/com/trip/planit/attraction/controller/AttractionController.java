package com.trip.planit.attraction.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip.planit.attraction.model.dto.AttractionSearchDto;
import com.trip.planit.attraction.model.dto.AttractionSearchResultDto;
import com.trip.planit.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
@Api(tags = { "명소 컨트롤러 API V1" })
public class AttractionController {

	private final AttractionService attractionService;

	@ApiOperation(value = "명소 검색 기능", notes = "명소 검색 기능입니다.")
	@GetMapping("/search")
	public ResponseEntity<List<AttractionSearchResultDto>> searchAttractions(@ModelAttribute AttractionSearchDto attractionSearchDto) throws SQLException {

		List<AttractionSearchResultDto> attractions = new ArrayList<>();
		attractions = attractionService.searchAttraction(attractionSearchDto);
		return new ResponseEntity<List<AttractionSearchResultDto>>(attractions, HttpStatus.OK);
	}
}
