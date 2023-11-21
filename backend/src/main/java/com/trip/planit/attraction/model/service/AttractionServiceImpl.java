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
	public List<AttractionSearchResultDto> findSido(AttractionSearchDto attractionSearchDto) throws SQLException {
		return attractionMapper.selectSido(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findSidoGugun(AttractionSearchDto attractionSearchDto) throws SQLException {
		return attractionMapper.selectSidoGugun(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findSidoKeyword(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectSidoKeyword(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findSidoGugunKeyword(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectSidoGugunKeyword(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findSidoContentTypeId(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectSidoContentTypeId(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findSidoGugunContentTypeId(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectSidoGugunContentTypeId(attractionSearchDto);
	}

	@Override
	public List<AttractionSearchResultDto> findSidoGugunKeywordContentTypeId(AttractionSearchDto attractionSearchDto)
			throws SQLException {
		return attractionMapper.selectSidoGugunKeywordContentTypeId(attractionSearchDto);
	}
}
