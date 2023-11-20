package com.trip.planit.attraction.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@ApiOperation(value = "명소를 검색합니다.", notes = "명소를 검색합니다")
	@GetMapping("/search")
	public ResponseEntity<List<AttractionSearchResultDto>> searchAttractions(
			@RequestParam(name = "sidoCode") String sidoCodeValue,
			@RequestParam(name = "keyword", required = false) String keyword) throws SQLException {
		AttractionSearchDto attractionSearchDto = new AttractionSearchDto();

		List<AttractionSearchResultDto> attractions = new ArrayList<>();

		int sidoCode = Integer.parseInt(sidoCodeValue);

		if (keyword == null) {
			attractionSearchDto.setSidoCode(sidoCode);
			attractions = attractionService.findAttractionBySearchNonKeyword(attractionSearchDto);
		} else {
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setKeyword(keyword);
			attractions = attractionService.findAttractionBySearch(attractionSearchDto);
		}

		return new ResponseEntity<List<AttractionSearchResultDto>>(attractions, HttpStatus.OK);
	}

	@ApiOperation(value = "컨텐츠 타입을 기반으로 명소를 검색합니다", notes = "컨텐츠 타입을 기반으로 명소를 검색합니다")
	@GetMapping("/")
	public ResponseEntity<List<AttractionSearchResultDto>> searchAttractionsByContentTypeId(
			@RequestParam(name = "sidoCode") String sidoCodeValue,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "contentTypeId") String contentTypeIdValue) throws SQLException {
		AttractionSearchDto attractionSearchDto = new AttractionSearchDto();

		int sidoCode = Integer.parseInt(sidoCodeValue);
		int contentTypeId = Integer.parseInt(contentTypeIdValue);
		
		List<AttractionSearchResultDto> attractions = new ArrayList<>();
		if(keyword == null) {
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setContentTypeId(contentTypeId);
			attractions = attractionService.findAttractionBySearchAndContentTypeIdNonKeyword(attractionSearchDto);
		}else {
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setKeyword(keyword);
			attractionSearchDto.setContentTypeId(contentTypeId);
			attractions = attractionService.findAttractionBySearchAndContentTypeId(attractionSearchDto);
		}
		
		return new ResponseEntity<List<AttractionSearchResultDto>>(attractions, HttpStatus.OK);
	}

}
