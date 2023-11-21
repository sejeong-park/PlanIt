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

	@ApiOperation(value = "명소 검색 기능", notes = "명소 검색 기능입니다.")
	@GetMapping("/search")
	public ResponseEntity<List<AttractionSearchResultDto>> searchAttractions(
			@RequestParam(name = "sidoCode", required = false) String sidoCodeValue,
			@RequestParam(name = "keyword", required = false) String keywordValue,
			@RequestParam(name = "gugun", required = false) String gugunCodeValue,
			@RequestParam(name = "contentTypeId", required = false) String contentTypeIdValue) throws SQLException {
		AttractionSearchDto attractionSearchDto = new AttractionSearchDto();

		List<AttractionSearchResultDto> attractions = new ArrayList<>();
		
		
		/**
		 * sido 코드로만 검색
			sido 코드, gugun 코드로만 검색
			sido 코드 + 키워드 검색
			sido 코드 + gugun 코드 키워드 검색
			시도 코드 + 컨텐츠 타입 검색
			시도 코드 + 구군 + 컨텐츠 타입 
			시도 코드 + 구군 + 키워드 + 컨텐츠 타입
		 */

		// sido코드로만 검색
		if(sidoCodeValue != null && gugunCodeValue == null && keywordValue == null && contentTypeIdValue == null) {
			int sidoCode = Integer.parseInt(sidoCodeValue);
			attractionSearchDto.setSidoCode(sidoCode);
			attractions = attractionService.findSido(attractionSearchDto);
		} // sido 코드 + gugun 코드로만 검색
		else if(sidoCodeValue != null && gugunCodeValue != null && keywordValue == null && contentTypeIdValue == null) {
			int sidoCode = Integer.parseInt(sidoCodeValue);
			int gugunCode = Integer.parseInt(gugunCodeValue);
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setGugunCode(gugunCode);
			attractions = attractionService.findSidoGugun(attractionSearchDto);
		}
		 // sido 코드 + 키워드로만 검색
		else if(sidoCodeValue != null && gugunCodeValue == null && keywordValue != null && contentTypeIdValue == null) {
			int sidoCode = Integer.parseInt(sidoCodeValue);
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setKeyword(keywordValue);
			attractions = attractionService.findSidoKeyword(attractionSearchDto);
		}
		// sido 코드 + gugunCode+키워드로만 검색
		else if(sidoCodeValue != null && gugunCodeValue != null && keywordValue != null && contentTypeIdValue == null) {
			int sidoCode = Integer.parseInt(sidoCodeValue);
			int gugunCode = Integer.parseInt(gugunCodeValue);
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setGugunCode(gugunCode);
			attractionSearchDto.setKeyword(keywordValue);
			attractions = attractionService.findSidoGugunKeyword(attractionSearchDto);
		}// 시도 코드 + 컨텐츠 타입 아이디 검색
		else if(sidoCodeValue != null && gugunCodeValue == null && keywordValue == null && contentTypeIdValue != null) {
			int contentTypeId = Integer.parseInt(contentTypeIdValue);
			int sidoCode = Integer.parseInt(sidoCodeValue);
			attractionSearchDto.setContentTypeId(contentTypeId);
			attractionSearchDto.setSidoCode(sidoCode);
			attractions = attractionService.findSidoContentTypeId(attractionSearchDto);
		}
		// 시도 코드 + 구군 +컨텐츠 타입 아이디 검색
		else if(sidoCodeValue != null && gugunCodeValue != null && keywordValue == null && contentTypeIdValue != null) {
			int contentTypeId = Integer.parseInt(contentTypeIdValue);
			int sidoCode = Integer.parseInt(sidoCodeValue);
			int gugunCode = Integer.parseInt(gugunCodeValue);
			attractionSearchDto.setContentTypeId(contentTypeId);
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setGugunCode(gugunCode);
			attractions = attractionService.findSidoGugunContentTypeId(attractionSearchDto);
		}// 시도 코드 + 구군 +키워드+컨텐츠 타입 아이디 검색
		else if(sidoCodeValue != null && gugunCodeValue != null && keywordValue != null && contentTypeIdValue != null) {
			int contentTypeId = Integer.parseInt(contentTypeIdValue);
			int sidoCode = Integer.parseInt(sidoCodeValue);
			int gugunCode = Integer.parseInt(gugunCodeValue);
			attractionSearchDto.setContentTypeId(contentTypeId);
			attractionSearchDto.setSidoCode(sidoCode);
			attractionSearchDto.setGugunCode(gugunCode);
			attractionSearchDto.setKeyword(keywordValue);
			attractions = attractionService.findSidoGugunKeywordContentTypeId(attractionSearchDto);
		}
		
		return new ResponseEntity<List<AttractionSearchResultDto>>(attractions, HttpStatus.OK);
	}
}
