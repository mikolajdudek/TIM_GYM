package com.example.gym.services;

import com.example.gym.dto.WardDetailsResponse;
import com.example.gym.dto.WardRequest;
import com.example.gym.dto.WardResponse;
import com.example.gym.entities.Ward;
import com.example.gym.repositories.TrainerRepository;
import com.example.gym.repositories.WardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WardService implements WardServiceInterface {

    private WardRepository wardRepository;
    private TrainerRepository trainerRepository;

    @Autowired
    public void WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }
    //get
    public List<WardResponse> getAll(){
        return wardRepository.findAll().stream().map(Ward::toResponse).collect(Collectors.toList());

    }
    //add
    public void addWards(WardRequest wardRequest){
        Ward ward = new Ward();
        ward.setIdWard(UUID.randomUUID());
        ward.setName(wardRequest.getName());
        ward.setSurname(wardRequest.getSurname());
        ward.setTrainer(trainerRepository.findByCode(wardRequest.getCode()));
        wardRepository.save(ward);
    }

    //delete
    public void deleteWards(String name){
        Ward e = wardRepository.findByName(name);
        wardRepository.delete(e);
    }

    //update
    @Transactional
    public void editWards(String name, WardRequest wardRequest){
        Ward ward = wardRepository.findByName(name);
        ward.setName(wardRequest.getName());
        ward.setSurname(wardRequest.getSurname());
        ward.setTrainer(trainerRepository.findByCode(wardRequest.getCode()));
        wardRepository.save(ward);
    }

    public WardDetailsResponse getByName(String name) {
        return wardRepository.findByName(name).toResponseDetails();
    }
}

