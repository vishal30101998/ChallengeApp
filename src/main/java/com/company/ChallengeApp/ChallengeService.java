package com.company.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

   // List<Challenge> challenges = new ArrayList<>();

    public ChallengeService() {

    }

    public List<Challenge> getChallenges() {
        return challengeRepository.findAll();
    }

    public boolean setChallenges(Challenge challenge) {

        if(challenge!=null){
            challengeRepository.save(challenge);

            return true;
        }
        return false;
    }


    public Challenge getChallengeByMonth(String month) {
        Optional<Challenge> ch =challengeRepository.findByMonthIgnoreCase(month);
        return ch.orElse(null);
    }


    public boolean updateChallenge(Long id, Challenge updateChallenge) {
        Optional<Challenge> ch =challengeRepository.findById(id);
        if(ch.isPresent()){
            Challenge toUpdateChallenge=ch.get();
            toUpdateChallenge.setDescription(updateChallenge.getDescription());
            toUpdateChallenge.setMonth(updateChallenge.getMonth());
            challengeRepository.save(toUpdateChallenge);
            return true;
        }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        Optional<Challenge> ch =challengeRepository.findById(id);
        if(ch.isPresent()){
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
