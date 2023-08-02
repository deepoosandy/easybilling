package com.sanapp.sms.domain.dream11.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NamedQuery(name="Match.findAll", query="SELECT i FROM Match i")
@Table(name = "dream11")
public class Match implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="player_name")
    private String playerName;

    @Column(name="team_code")
    private String teamCode;

    @Column(name="player_score")
    private Double playerScore;
}
