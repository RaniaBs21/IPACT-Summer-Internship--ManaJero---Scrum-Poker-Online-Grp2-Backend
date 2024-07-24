package com.example.manajeroback.Models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JiraIssuesResponse {
    private String id;
    private String platformId;
    private String text;
    private String status;
}
