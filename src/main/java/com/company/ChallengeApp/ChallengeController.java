package com.company.ChallengeApp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {
    ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
       this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getChallenges() {
        return new ResponseEntity<>(challengeService.getChallenges(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> setChallenges(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.setChallenges(challenge);
        if (isChallengeAdded) {
            return new ResponseEntity<>("Challenge added",HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not added",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallengeByMonth(@PathVariable String month) {
       Challenge challenge =  challengeService.getChallengeByMonth(month);
       if (challenge == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(challenge,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updateChallenge) {

        boolean isChallengeUpdated = challengeService.updateChallenge(id,updateChallenge);
        if (isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not updated",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {

        boolean isChallengeDeleted =  challengeService.deleteChallenge(id);

        if (isChallengeDeleted) {
            return new ResponseEntity<>("Challenge deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not deleted",HttpStatus.NOT_FOUND);
    }
}
