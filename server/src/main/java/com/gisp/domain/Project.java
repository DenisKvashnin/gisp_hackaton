package com.gisp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("project")
public class Project {
    @Id
    private Long id;
}
