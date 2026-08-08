package com.example.lab7_6733803909_5_sec4.repository;

import com.example.lab7_6733803909_5_sec4.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
