package com.trip.planit.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.planit.attraction.model.dto.AttractionSearchResultDto;
import com.trip.planit.attraction.model.dto.AttractionSearchDto;
import com.trip.planit.attraction.model.mapper.AttractionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
	
	private final AttractionMapper attractionMapper;

	@Override
	public List<AttractionSearchResultDto> findAttractionBySearch(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectAttractionBySearch(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findAttractionBySearchNonKeyword(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectAttractionBySearchNonKeyword(attractionSearchDto);
	}
	
	@Override
	public List<AttractionSearchResultDto> findAttractionBySearchAndContentTypeId(
			AttractionSearchDto attractionSearchDto) throws SQLException {
		return attractionMapper.selectAttractionBySearchAndContentTypeId(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findAttractionBySearchAndContentTypeIdNonKeyword(
			AttractionSearchDto attractionSearchDto) throws SQLException {

		return attractionMapper.selectAttractionBySearchAndContentTypeIdNonKeyword(attractionSearchDto);
	}
}
