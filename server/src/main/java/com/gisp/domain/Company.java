package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Table("company")
public class Company {
    private String full_name;
    private String email;
    private String electronic_trading_platforms;
    private String okved2;
    private String type_organization;
    private String kind_of_activity_tass;
    private String kind_of_activity_dop_tass;
    private String legal_org_form;
    private String type_company;
    private String status_company;
    private String date_reg_with_tax_authority;
    private String code_tax_authority;
    private String fact_address;
    private String attribute_company;
    private String name;
    private String kind_company;
    private String branch;
    private String ogrn;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String inn;
    private String kpp;
    private String region;
    private String address;
    private String contact_email;
    private String address_site;
    private String contact_phone;
}
