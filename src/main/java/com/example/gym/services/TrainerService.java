package com.example.gym.services;

import com.example.gym.dto.TrainerRequest;
import com.example.gym.dto.TrainerResponse;
import com.example.gym.dto.WardResponse;
import com.example.gym.entities.Trainer;
import com.example.gym.entities.Ward;
import com.example.gym.repositories.TrainerRepository;
import com.example.gym.repositories.WardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainerService implements TrainerServiceInterface {
    private TrainerRepository trainerRepository;
    private WardRepository wardRepository;

    @Autowired
    public void TrainerService(TrainerRepository trainerRepository, WardRepository wardRepository) {
        this.trainerRepository = trainerRepository;
        this.wardRepository  = wardRepository;
    }

    //all trainer
    public List<TrainerResponse> getAll() {
        return trainerRepository.findAll().stream().map(n -> {
            List<WardResponse> wardResponses = wardRepository.findAllByTrainer(n).stream().map(e -> e.toResponse()).collect(Collectors.toList());
            return n.toResponse(wardResponses);
        }).collect(Collectors.toList());
    }

    //dodawanie manager
    public TrainerResponse addTrainer(TrainerRequest request) {
        Trainer trainer = new Trainer();
        trainer.setName(request.getName());
        trainer.setSurname(request.getSurname());


        long n3 = Math.round(Math.random()*1000);
        String a = String.valueOf(request.getName().charAt(0));
        String b = String.valueOf(request.getSurname().charAt(0));
        trainer.setCode(new StringBuilder(a).append(b).append(n3).toString());
        return trainerRepository.save(trainer).toResponse();
    }

    //usuwanie manager
    public void deleteTrainer(String code) {
        Trainer trainer = trainerRepository.findByCode(code);
        List<Ward> l = wardRepository.findAllByTrainer(trainer);
        l.forEach(e -> wardRepository.delete(e));
        trainerRepository.delete(trainer);
    }

    //edit
    public TrainerResponse editTrainer(String code, TrainerRequest request) {
        Trainer trainer = trainerRepository.findByCode(code);
        trainer.setName(request.getName());
        trainer.setSurname(request.getSurname());

        long n3 = Math.round(Math.random()*1000);
        String a = String.valueOf(request.getName().charAt(0));
        String b = String.valueOf(request.getSurname().charAt(0));
        trainer.setCode(new StringBuilder(a).append(b).append(n3).toString());
        return trainerRepository.save(trainer).toResponse();
    }

    public TrainerResponse getTrainer(String code) {
        Trainer n = trainerRepository.findByCode(code);
        List<WardResponse> wardsResponses = wardRepository.findAllByTrainer(n).stream().map(e -> e.toResponse()).collect(Collectors.toList());
        return n.toResponse(wardsResponses);
    }
}
