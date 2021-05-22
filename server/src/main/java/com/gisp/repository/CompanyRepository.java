package com.gisp.repository;

import com.gisp.domain.Company;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, String> {
    List<Company> findAll();

    @Query("SELECT * FROM company WHERE inn = :company_inn")
    Company findByInn(@Param("company_inn") String companyInn);



}

