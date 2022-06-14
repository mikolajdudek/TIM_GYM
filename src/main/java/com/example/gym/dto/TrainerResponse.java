package com.example.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerResponse {
    private String name;
    private String surname;
    private String code;
    private List<WardResponse> ward;
}
