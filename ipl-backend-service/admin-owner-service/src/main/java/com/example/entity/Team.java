package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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

    @Column(name="team_name")
    private String teamName;

    @Column(name="owner_name")
    private String ownerName;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="email_id")
    private String emailId;

    @Column(name="temp_password")
    private String tempPassword;

    @Column(name="team_url")
    private String teamUrl;


}
