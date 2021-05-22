package com.gisp.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterSupportDTO {
    private String startSum;
    private String endSum;
    private String measureType;
    private String srokSubsidy;
    private String procentSubsidy;
    private String srokVozvrata;
    private String startPprocentVozvrata;
    private String endProcentVozvrata;
    private String isSofinance;
    private List<String> ids;
}
