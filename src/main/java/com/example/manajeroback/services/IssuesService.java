package com.example.manajeroback.services;

import com.example.manajeroback.Models.IssuesRequest;
import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.repositories.IssuesRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssuesService {


    private final IssuesRepository issuesRepository;

//    @Value("${jira.apiToken}")
//    private String jiraApiToken;
//
//    @Value("${jira.email}")
//    private String jiraUserEmail;
//
//    @Value("${azure.apiToken}")
//    private String AzureApiToken;
//
//    @Value("${azure.email}")
//    private String AzureUserEmail;
//
//    @Autowired
//    private HttpHeaders httpHeaders;
//
//    @Autowired
//    private RestTemplate restTemplate;


    public Issues addIssues(Issues Issues) {
        return issuesRepository.save(Issues);
    }

    public Issues addIssuesBySession(Issues Issues, String sesionId) {
        return issuesRepository.save(Issues);
    }


    public Issues updateIssues(Issues Issues) {

        return issuesRepository.save(Issues);
    }


    public void deleteIssues(String id) {
        issuesRepository.deleteById(id);

    }


    public List<Issues> displayUserStories() {
        return issuesRepository.findAll();

    }


    public Issues getIssuesById(String id) {
        return issuesRepository.findById(id).orElse(null);
    }


    public Integer uploadIssues(MultipartFile file, String sessionId) throws IOException {
        Set<Issues> issues = parseCsv(file);
        issuesRepository.saveAll(issues);
        return issues.size();
    }

    private Set<Issues> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<IssuesCsvRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(IssuesCsvRepresentation.class);
            CsvToBean<IssuesCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<IssuesCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> Issues.builder()
                                    .name(csvLine.getName())
                                    .description(csvLine.getDescription())
//                            .estimation(csvLine.getEstimation())
//                            .status(csvLine.getStatus())
//                            .emittedVotes(csvLine.getEmittedVotes())
                                    .build()
                    )
                    .collect(Collectors.toSet());
        }
    }

    //import Issues from jira to db
    public List<Issues> findBySessionId(String sessionId) {
        return issuesRepository.findBySessionId(sessionId);
    }

    public void insertIssues1(List<IssuesRequest> newUserStories, String sessionId) {
        List<Issues> userstories = new ArrayList<>();

        for (var Issues : newUserStories) {
            var us = new Issues();
            us.setDescription(Issues.getDescription());
            us.setSessionId(sessionId);
            userstories.add(us);
        }
        issuesRepository.saveAll(userstories);
    }
}
