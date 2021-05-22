package com.gisp.service;

import com.gisp.domain.Company;
import com.gisp.domain.UserCompany;
import com.gisp.repository.UserCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCompanyService {

    private final UserCompanyRepository userCompanyRepository;
    private final CompanyService companyService;

    public UserCompanyService(UserCompanyRepository userCompanyRepository,
                              CompanyService companyService) {
        this.userCompanyRepository = userCompanyRepository;
        this.companyService = companyService;
    }

    public List<Company> findAll() {
        List<UserCompany> userCompanies = userCompanyRepository.findAll();
        List<Company> companies = new ArrayList<>();
        for (var userCompany : userCompanies) {
            Company company = companyService.findByInn(userCompany.getCompanyInn());
            if (company != null) {
                companies.add(company);
            }
        }
        return companies;
    }

    public Company saveByInn(String innCompany) {
        UserCompany userCompany = userCompanyRepository.findByInn(innCompany);
        if (userCompany == null) {
            UserCompany newUserCompany = new UserCompany();
            newUserCompany.setId(null);
            newUserCompany.setCompanyInn(innCompany);
            userCompanyRepository.save(newUserCompany);
            return companyService.findByInn(innCompany);
        }
        return null;
    }

    public Company save(Company company) throws IllegalAccessException {
        String inn = company.getInn();
        if (inn == null) {
            throw new IllegalAccessException("inn is null");
        }
        return companyService.save(company);
    }
}
