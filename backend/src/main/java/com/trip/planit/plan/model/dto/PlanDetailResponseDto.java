package com.trip.planit.plan.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlanDetailResponseDto {
    private int detailId;
    private Date planDate;
    private String planKey;
    private int sequence;
    private String title;
    private String content;
    private int attractionId;
    private int contentTypeId;
    private String addr1;
    private String addr2;
    private String firstImage;
    private String sidoCode;
    private String gugunCode;
    private String latitude;
    private String longitude;
    private String overview;
}
