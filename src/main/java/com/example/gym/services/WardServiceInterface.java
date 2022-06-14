package com.example.gym.services;

import com.example.gym.dto.WardRequest;
import com.example.gym.dto.WardResponse;

import java.util.List;

public interface WardServiceInterface {
    List<WardResponse> getAll();

    void addWards(WardRequest wardRequest);
}
