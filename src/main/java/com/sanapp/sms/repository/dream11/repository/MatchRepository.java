package com.sanapp.sms.repository.dream11.repository;

import com.sanapp.sms.domain.dream11.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByTeamCode(String teamCode);
}
