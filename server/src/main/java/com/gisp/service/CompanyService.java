package com.gisp.service;

import com.gisp.domain.Company;
import com.gisp.repository.CompanyRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String COMPANY_INSERT = "INSERT INTO public.company(\n" +
            "\tfull_name, email, electronic_trading_platforms, okved2, type_organization, kind_of_activity_tass, kind_of_activity_dop_tass, legal_org_form, type_company, status_company, date_reg_with_tax_authority, code_tax_authority, fact_address, attribute_company, name, kind_company, branch, ogrn, inn, kpp, region, address, contact_email, address_site, contact_phone)\n" +
            "\tVALUES (:full_name, :email, :electronic_trading_platforms, :okved2, :type_organization, :kind_of_activity_tass, :kind_of_activity_dop_tass, :legal_org_form, :type_company, :status_company, :date_reg_with_tax_authority, :code_tax_authority, :fact_address, :attribute_company, :name, :kind_company, :branch, :ogrn, :inn, :kpp, :region, :address, :contact_email, :address_site, :contact_phone)";

    private String COMPANY_UPDATE = "UPDATE public.company\n" +
            "\tSET full_name=:name, email=:email, electronic_trading_platforms=:electronic_trading_platforms, okved2=:okved2, type_organization=:type_organization, kind_of_activity_tass=:kind_of_activity_tass, kind_of_activity_dop_tass=:kind_of_activity_dop_tass, legal_org_form=:legal_org_form, type_company=:type_company, status_company=:status_company, date_reg_with_tax_authority=:date_reg_with_tax_authority, code_tax_authority=:code_tax_authority, fact_address=:fact_address, attribute_company=:attribute_company, name=:name, kind_company=:kind_company, branch=:branch, ogrn=:ogrn, inn=:inn, kpp=:kpp, region=:region, address=:address, contact_email=:contact_email, address_site=:address_site, contact_phone=:contact_phone\n" +
            "\tWHERE inn = :inn";

    public CompanyService(CompanyRepository companyRepository,
                          NamedParameterJdbcTemplate jdbcTemplate) {

        this.companyRepository = companyRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company save(Company company) {

        Company foundCompany = companyRepository.findByInn(company.getInn());

        Map<String, Object> params = new HashMap<>();
        params.put("inn" , company.getInn());
        params.put("full_name" , company.getFull_name());
        params.put("email" , company.getEmail());
        params.put("electronic_trading_platforms" , company.getElectronic_trading_platforms());
        params.put("okved2" , company.getOkved2());
        params.put("type_organization" , company.getType_organization());
        params.put("kind_of_activity_tass" , company.getKind_of_activity_tass());
        params.put("kind_of_activity_dop_tass" , company.getKind_of_activity_dop_tass());
        params.put("legal_org_form" , company.getLegal_org_form());
        params.put("type_company" , company.getType_company());
        params.put("status_company" , company.getStatus_company());
        params.put("date_reg_with_tax_authority" , company.getDate_reg_with_tax_authority());
        params.put("code_tax_authority" , company.getCode_tax_authority());
        params.put("fact_address" , company.getFact_address());
        params.put("attribute_company" , company.getAttribute_company());
        params.put("name" , company.getName());
        params.put("kind_company" , company.getKind_company());
        params.put("branch" , company.getBranch());
        params.put("ogrn" , company.getOgrn());
        params.put("kpp" , company.getKpp());
        params.put("region" , company.getRegion());
        params.put("address" , company.getAddress());
        params.put("contact_email" , company.getContact_email());
        params.put("address_site" , company.getAddress_site());
        params.put("contact_phone" , company.getContact_phone());

        if (foundCompany == null) {
            jdbcTemplate.update(COMPANY_INSERT, params);
        } else {
            jdbcTemplate.update(COMPANY_UPDATE, params);
        }
        return companyRepository.findByInn(company.getInn());
    }

    public Company findByInn(String companyInn) {
        return companyRepository.findByInn(companyInn);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
