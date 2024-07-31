package com.example.manajeroback.Controllers;

import com.example.manajeroback.Models.IssuesRequest;
import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.services.IssuesServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @PostMapping("/insert/session/{sessionId}")
    public void insertIssues1(@RequestBody List<IssuesRequest> issues, @PathVariable String sessionId) {
        issuesServices.insertIssues1(issues, sessionId);
    }

    @PostMapping("/importedAzure/{ids}")
    public void insertIssuesAzure(@PathVariable Integer ids) {

    }


    @PostMapping(value = "/upload/{sessionId}", consumes = {"multipart/form-data"})
    public Integer uploadIssues(@RequestPart("file") MultipartFile file, @PathVariable String sessionId) throws IOException {
        return issuesServices.uploadIssues(file,sessionId);
    }

    @GetMapping("/{id}")
    public Issues getIssuesById(@PathVariable String id) {
        return issuesServices.getIssuesById(id);
    }




}
