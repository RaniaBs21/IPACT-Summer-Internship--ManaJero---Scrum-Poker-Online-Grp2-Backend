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

    @PostMapping("/addIssue")
    Issues addIssue(@RequestBody Issues issues) {
        return  issuesServices.addIssues(issues);
    }


    @GetMapping("/getIssues")
    List<Issues> getIssues(){
        return issuesServices.getAllIssues();
    }

    @GetMapping("/getIssues/{id}")
    public Issues getIssuesById(@PathVariable String id) {
        return issuesServices.getIssueById(id);
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
