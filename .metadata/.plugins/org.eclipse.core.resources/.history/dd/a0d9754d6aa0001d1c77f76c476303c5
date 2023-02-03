package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="team_details")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer","FieldHandler"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    private String teamName;
    private String ownerName;
    private String city;
    private String state;
    private String emailId;
    private String tempPassword;
    private String teamUrl;

    @OneToMany(targetEntity = Player.class)
    @JoinTable(name="team_players",
    joinColumns = @JoinColumn(name="team_id"),
    inverseJoinColumns = @JoinColumn(name="player_id"))
    @JsonBackReference
    private List<Player> players;


}
