package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("branch")
public class Branch {
    @Id
    private Long id;
    private String name;
}
