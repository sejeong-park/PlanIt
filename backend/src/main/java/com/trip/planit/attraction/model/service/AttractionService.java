package com.trip.planit.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.attraction.model.dto.AttractionSearchResultDto;
import com.trip.planit.attraction.model.dto.AttractionSearchDto;

public interface AttractionService {
	List<AttractionSearchResultDto> searchAttraction(AttractionSearchDto attractionSearchDto) throws SQLException;
}
