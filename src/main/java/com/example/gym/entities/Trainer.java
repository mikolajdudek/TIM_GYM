package com.example.gym.entities;

import com.example.gym.dto.TrainerResponse;
import com.example.gym.dto.WardResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Trainer {
    @Id
    @Column(name = "id_trainer")
    private UUID idTrainer;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "code_trainer")
    private String code;

    public TrainerResponse toResponse(List<WardResponse> wardsResponses){
        return new TrainerResponse(name, surname, code, wardsResponses);
    }

    public TrainerResponse toResponse(){
        return new TrainerResponse(name, surname, code,null);
    }

}
