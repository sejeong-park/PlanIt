package com.trip.planit.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.attraction.model.dto.AttractionSearchResultDto;
import com.trip.planit.attraction.model.dto.AttractionSearchDto;

public interface AttractionService {
	List<AttractionSearchResultDto> findSido(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> findSidoGugun(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> findSidoKeyword(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> findSidoGugunKeyword(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> findSidoContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> findSidoGugunContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> findSidoGugunKeywordContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
}
