package com.example.gym.entities;


import com.example.gym.dto.WardDetailsResponse;
import com.example.gym.dto.WardResponse;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ward")
    private Integer idWard;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trainer")
    private Trainer trainer;

    public WardResponse toResponse(){
        return new WardResponse(name, surname);
    }

    public WardDetailsResponse toResponseDetails(){
        return new WardDetailsResponse(name, surname, trainer.getName(), trainer.getCode());
    }

}
