package com.sanapp.sms.dto.dream1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
@Builder
public class MatchData {
    private String playerName;
    private Double[] scores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchData)) return false;

        MatchData matchData = (MatchData) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getScores(), matchData.getScores());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getScores());
    }
}


