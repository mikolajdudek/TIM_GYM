package com.example.gym.repositories;

import com.example.gym.entities.Trainer;
import com.example.gym.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WardRepository extends JpaRepository<Ward, UUID> {
    List<Ward> findAllByTrainer(Trainer n);

    Ward findByName(String name);
}
