package com.gisp.domain;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("desc_struct_measure_support")
public class DescriptionStructureMeasureSupport {
    private String fieldName;
    private String englishDescription;
    private String russianDescription;
    private String format;
}
