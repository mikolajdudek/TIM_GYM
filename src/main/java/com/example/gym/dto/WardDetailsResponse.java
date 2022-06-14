package com.example.gym.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WardDetailsResponse {
    private String name;
    private String surname;
    private String trainerName;
    private String code;
}
