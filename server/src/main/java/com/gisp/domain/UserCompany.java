package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_company")
public class UserCompany {
    @Id
    private Long id;
    private String companyInn;
}
