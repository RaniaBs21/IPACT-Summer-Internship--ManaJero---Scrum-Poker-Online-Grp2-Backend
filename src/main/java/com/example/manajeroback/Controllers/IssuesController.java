package com.example.manajeroback.Controllers;

import com.example.manajeroback.Models.IssuesRequest;
import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.services.IssuesService;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class IssuesController {

    private final IssuesService service;


        @GetMapping("/getIssues")
        public List<Issues> displayUserStories() {
            return service.displayUserStories();

        }

        @PostMapping("/addIssues")
        public void addIssues(@RequestBody Issues issues) {
            service.addIssues(issues);
        }
        @PostMapping("/ajoutIssues/{sessionId}")
        public void addIssuesBySession(@RequestBody Issues issues,String sessionId) {
            service.addIssuesBySession(issues,sessionId);
        }

        @DeleteMapping("/deleteIssues/{id}")
        public void deleteIssues(@PathVariable String id) {
            service.deleteIssues(id);
        }

        @PutMapping("/{id}")
        Issues updateIssues(@PathVariable String id, @RequestBody Issues updatedIssues) {
            Issues existingIssues = service.getIssuesById(id);
            existingIssues.setName(updatedIssues.getName());
            existingIssues.setDescription(updatedIssues.getDescription());
            return service.updateIssues(existingIssues);
        }

        @PostMapping("/insert/session/{sessionId}")
        public void insertIssues1(@RequestBody List<IssuesRequest> issues, @PathVariable String sessionId) {
            service.insertIssues1(issues, sessionId);
        }

        @PostMapping("/importedAzure/{ids}")
        public void insertIssuesAzure(@PathVariable Integer ids) {

        }


        @PostMapping(value = "/upload/{sessionId}", consumes = {"multipart/form-data"})
        public Integer uploadIssues(@RequestPart("file") MultipartFile file, @PathVariable String sessionId) throws IOException {
            return service.uploadIssues(file,sessionId);
        }

        @GetMapping("/{id}")
        public Issues getIssuesById(@PathVariable String id) {
            return service.getIssuesById(id);
        }


        @GetMapping("/session/{sessionId}")
        public List<Issues> getIssuesBySessionId(@PathVariable String sessionId){
            return service.findBySessionId(sessionId);
        }

}
