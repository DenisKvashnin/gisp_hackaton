package com.gisp.repository;

import com.gisp.domain.UserCompany;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCompanyRepository extends CrudRepository<UserCompany, Long> {
    List<UserCompany> findAll();

    @Query("SELECT * FROM user_company WHERE company_inn = :inn")
    UserCompany findByInn(@Param("inn") String innCompany);
}
