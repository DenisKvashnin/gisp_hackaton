package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_count")
public class UserCount {
    @Id
    private Long id;
    private String egrulRegdate;
    private String inn;
    private String kpp;
    private String ogrn;
    private String nameFull;
    private String nameShort;
    private String peopleCount;
}
