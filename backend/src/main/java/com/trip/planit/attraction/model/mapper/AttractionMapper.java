package com.trip.planit.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.attraction.model.dto.AttractionSearchResultDto;
import com.trip.planit.attraction.model.dto.AttractionSearchDto;


@Mapper
public interface AttractionMapper {
	List<AttractionSearchResultDto> selectSido(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectSidoGugun(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectSidoKeyword(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectSidoGugunKeyword(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectSidoContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectSidoGugunContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
	List<AttractionSearchResultDto> selectSidoGugunKeywordContentTypeId(AttractionSearchDto attractionSearchDto) throws SQLException;
}
