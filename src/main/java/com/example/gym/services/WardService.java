package com.example.gym.services;

import com.example.gym.dto.WardDetailsResponse;
import com.example.gym.dto.WardRequest;
import com.example.gym.dto.WardResponse;
import com.example.gym.entities.Authority;
import com.example.gym.entities.User;
import com.example.gym.entities.Ward;
import com.example.gym.repositories.AuthoritiesRepository;
import com.example.gym.repositories.TrainerRepository;
import com.example.gym.repositories.UserRepository;
import com.example.gym.repositories.WardRepository;
import com.example.gym.utils.Password;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
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
    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public void WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }
    //get
    public List<WardResponse> getAll(){
        return wardRepository.findAll().stream().map(Ward::toResponse).collect(Collectors.toList());

    }
    //add
    @Transactional
    public void addWards(WardRequest wardRequest){
        var id = UUID.randomUUID();

        //User user = new User(id, wardRequest.getUsername(), "{bcrypt}"+ Password.hashPassword(wardRequest.getPassword()), true);
       // userRepository.save(user);

       // Authority auth = new Authority(wardRequest.getUsername());
       // authoritiesRepository.save(auth);

        Ward ward = new Ward();
        ward.setIdWard(id);
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

