package com.example.gym.services;

import com.example.gym.dto.TrainerRequest;
import com.example.gym.dto.TrainerResponse;

import java.util.List;

public interface TrainerServiceInterface {
    List<TrainerResponse> getAll();

    TrainerResponse addTrainer(TrainerRequest trainerRequest);
}
