package com.example.repository;


import com.example.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team,Integer> {

    @Query(value = "select team_id from team_details where owner_name = ?",nativeQuery = true)
    Integer findIdByName(String ownername);
}
