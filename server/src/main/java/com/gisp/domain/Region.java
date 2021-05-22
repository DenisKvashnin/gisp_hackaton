package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("region")
public class Region {
    @Id
    private Long id;
    private String name;
    private String type;
    private String nameWithType;
    private String federalDistrict;
    private String kladrId;
    private String fiasId;
    private String okato;
    private String oktmo;
    private String taxOffice;
    private String postalCode;
    private String isoCode;
    private String timezone;
    private String geonameCode;
    private String geonameId;
    private String geonameName;
}
