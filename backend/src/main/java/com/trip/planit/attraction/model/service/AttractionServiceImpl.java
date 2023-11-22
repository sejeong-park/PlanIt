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
	public List<AttractionSearchResultDto> searchAttraction(AttractionSearchDto attractionSearchDto) throws SQLException {
		return attractionMapper.selectAttractionBySearch(attractionSearchDto);
	}
}
