package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="player_detail")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    private String playerName;
    private Integer age;
    private Boolean isForegin;
    private Boolean isAvailable;
    private String specialty;
    private String imageUrl;
    private String nationality;

    @ManyToOne(targetEntity = Team.class)
    @JoinTable(	name = "team_players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Team team;


}
