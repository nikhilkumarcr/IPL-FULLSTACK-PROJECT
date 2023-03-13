package com.example.repository;


import com.example.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
    @Query(value = "select * from team_lists where owner_name=? ", nativeQuery = true)
    public  Team getIdByName(String ownerName);
}
