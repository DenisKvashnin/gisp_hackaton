package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("okved")
public class Okved {
    @Id
    private Long id;
    private Long branchId;
    private Long branch2Id;
    private Long branch3Id;
    private String number;
    private String description;
}
