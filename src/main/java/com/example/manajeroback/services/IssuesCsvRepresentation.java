package com.example.manajeroback.services;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssuesCsvRepresentation {

    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "description")
    private String description;


}
