package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.services.IssuesServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class IssuesController {
    IssuesServices issuesServices;
    @PostMapping("/session/{sessionId}")
    public Issues addIssue(@PathVariable String sessionId, @RequestBody Issues issue) {
        return issuesServices.addIssue(sessionId, issue);
    }

    @GetMapping("/getIssues")
    List<Issues> getIssues(){
        return issuesServices.getAllIssues();
    }

    @GetMapping("/session/{sessionId}")
    public List<Issues> getIssuesBySessionId(@PathVariable String sessionId) {
        return issuesServices.getIssuesBySessionId(sessionId);
    }

    @PutMapping("/updateIssue/{id}")
    public Issues updateIssue(@PathVariable String id, @RequestBody Issues issues) {
        return issuesServices.updateIssues(issues, id);

    }

    @DeleteMapping("/deleteIssue/{id}")
    void deleteIssue(@PathVariable String id) {
        issuesServices.deleteIssues(id);
    }


}
