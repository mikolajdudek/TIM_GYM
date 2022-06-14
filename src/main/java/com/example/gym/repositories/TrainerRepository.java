package com.example.gym.repositories;

import com.example.gym.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Trainer findByName(String name);

    Trainer findByCode(String code);
}
