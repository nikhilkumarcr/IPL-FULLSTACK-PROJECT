package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="player_lists")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column(name="player_name")
    private String playerName;

    @Column(name="age")
    private Integer age;

    @Column(name = "available")
    private Boolean available;

    @Column(name="specialty")
    private String specialty;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name="nationality")
    private String nationality;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name="team_id")
    private Team team;


}
