package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="player_details")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    private String playerName;
    private Integer age;
    private Boolean available;
    private String specialty;
    private String imageUrl;
    private String nationality;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name="team_id")
    private Team team;


}
