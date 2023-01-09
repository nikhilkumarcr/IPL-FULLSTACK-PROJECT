package com.example.repository;


import com.example.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

    @Query(value="select * from player_details where team_id=?",nativeQuery = true)
    public List<Player> getByTeamId(Integer teamId);
}
