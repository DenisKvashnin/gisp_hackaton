package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("measureSupport")
public class MeasureSupport {
    @Id
    private Long id;
    private String url;
    private String smallName;
    private String fullName;
    private String numberNpa;
    private String dateNpa;
    private String npaName;
    private String description;
    private String purpose;
    private String objective;
    private String typeMera;
    private String typeFormatSupport;
    private String srokVozvrata;
    private String procentVozvrata;
    private String guarantePeriode;
    private String guaranteeCost;
    private String ApplianceId;
    private String okved2;
    private String complexity;
    private String amountOfSupport;
    private String regularitySelect;
    private String period;
    private String dogovor;
    private String gosProgram;
    private String event;
    private String dopInfo;
    private String isNotActive;
    private String prichinaNotAct;
    private String reqZayavitel;
    private String requestProject;
    private String isSofinance;
    private String dolyaIsofinance;
    private String budgetProject;
    private String pokazatelResult;
    private String territorialLevel;
    private String regionId;
    private String responsStructure;
    private String orgId;
}
