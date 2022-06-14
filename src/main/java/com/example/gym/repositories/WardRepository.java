package com.example.gym.repositories;

import com.example.gym.entities.Trainer;
import com.example.gym.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    List<Ward> findAllByTrainer(Trainer n);

    Ward findByName(String name);
}
