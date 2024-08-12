package com.example.manajeroback.services;

import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.VoteRepository;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {


    private VoteRepository voteRepository;

    public Vote addVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> getVotesBySessionAndIssue(String sessionId, String issueId) {
        return voteRepository.findBySessionIdAndIssueId(sessionId, issueId);
    }

    public double calculateAverageVote(String sessionId, String issueId) {
        List<Vote> votes = voteRepository.findBySessionIdAndIssueId(sessionId, issueId);
        if (votes.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        int count = 0;

        for (Vote vote : votes) {
            String voteValueStr = vote.getVote();

            if (voteValueStr.equals("☕️") || voteValueStr.equals("☕")) {
                continue; // Ignore coffee votes
            }

            double voteValue;
            try {
                // Try to parse numeric value
                voteValue = Double.parseDouble(voteValueStr);
            } catch (NumberFormatException e) {
                // If parsing fails, convert alphabetical votes to a numeric value
                voteValue = convertAlphaVoteToNumeric(voteValueStr);
            }

            sum += voteValue;
            count++;
        }

        return count == 0 ? 0.0 : sum / count;
    }

    private double convertAlphaVoteToNumeric(String vote) {
        switch (vote.toUpperCase()) {
            case "XS":
            case "1":
                return 1;
            case "S":
            case "2":
                return 2;
            case "M":
            case "3":
                return 3;
            case "L":
            case "4":
                return 4;
            case "XL":
            case "5":
                return 5;
            default:
                return 0; // Default to 0 if vote is unrecognized
        }

    }

  /*public Object calculateAverageVote(String sessionId, String issueId) {
      List<Vote> votes = voteRepository.findBySessionIdAndIssueId(sessionId, issueId);
      if (votes.isEmpty()) {
          return 0.0;
      }

      double sum = 0.0;
      int count = 0;
      boolean hasNumericVotes = false;

      for (Vote vote : votes) {
          String voteValueStr = vote.getVote();

          if (voteValueStr.equals("☕️") || voteValueStr.equals("☕")) {
              continue; // Ignore coffee votes
          }

          double voteValue;
          try {
              // Try to parse numeric value
              voteValue = Double.parseDouble(voteValueStr);
              hasNumericVotes = true;
          } catch (NumberFormatException e) {
              // If parsing fails, convert alphabetical votes to a numeric value
              voteValue = convertAlphaVoteToNumeric(voteValueStr);
          }

          sum += voteValue;
          count++;
      }

      if (count == 0) {
          return 0.0;
      }

      double average = sum / count;

      // Return average as numeric if any numeric votes were present
      if (hasNumericVotes) {
          return average;
      } else {
          // Otherwise, return average as alphabetic vote
          return convertNumericToAlphaVote(average);
      }
  }

    private double convertAlphaVoteToNumeric(String vote) {
        switch (vote.toUpperCase()) {
            case "XS":
                return 1;
            case "S":
                return 2;
            case "M":
                return 3;
            case "L":
                return 4;
            case "XL":
                return 5;
            default:
                return 0; // Default to 0 if vote is unrecognized
        }
    }

    private String convertNumericToAlphaVote(double average) {
        if (average < 1.5) {
            return "XS";
        } else if (average < 2.5) {
            return "S";
        } else if (average < 3.5) {
            return "M";
        } else if (average < 4.5) {
            return "L";
        } else {
            return "XL";
        }
    }*/
}
