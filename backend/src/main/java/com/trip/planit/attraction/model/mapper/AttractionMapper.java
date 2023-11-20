package com.trip.planit.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.attraction.model.dto.AttractionSearchResultDto;
import com.trip.planit.attraction.model.dto.AttractionSearchDto;


@Mapper
public interface AttractionMapper {
	List<AttractionSearchResultDto> selectAttractionBySearch(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectAttractionBySearchNonKeyword(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectAttractionBySearchAndContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectAttractionBySearchAndContentTypeIdNonKeyword(AttractionSearchDto attractionSearchDto) throws SQLException;
}
