package com.example.manajeroback.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssuesRequest {
    private String description;
    private String platformId;
}
