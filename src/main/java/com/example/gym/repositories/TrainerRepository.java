package com.example.gym.repositories;

import com.example.gym.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//zamiast uuid integer
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
    Trainer findByName(String name);

    Trainer findByCode(String code);
}
