package com.example.gym.services;

import com.example.gym.dto.TrainerRequest;
import com.example.gym.dto.TrainerResponse;
import com.example.gym.dto.WardResponse;
import com.example.gym.entities.Authority;
import com.example.gym.entities.Trainer;
import com.example.gym.entities.User;
import com.example.gym.entities.Ward;
import com.example.gym.repositories.AuthoritiesRepository;
import com.example.gym.repositories.TrainerRepository;
import com.example.gym.repositories.UserRepository;
import com.example.gym.repositories.WardRepository;
import com.example.gym.utils.Password;
import com.example.gym.utils.TokenPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainerService implements TrainerServiceInterface {
    private TrainerRepository trainerRepository;
    private WardRepository wardRepository;
    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public void TrainerService(TrainerRepository trainerRepository, WardRepository wardRepository, UserRepository userRepository,
                               AuthoritiesRepository authoritiesRepository) {
        this.trainerRepository = trainerRepository;
        this.wardRepository = wardRepository;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.objectMapper = new ObjectMapper();
    }

    //all trainer
    public List<TrainerResponse> getAll() {
        return trainerRepository.findAll().stream().map(n -> {
            List<WardResponse> wardResponses = wardRepository.findAllByTrainer(n).stream().map(e -> e.toResponse()).collect(Collectors.toList());
            return n.toResponse(wardResponses);
        }).collect(Collectors.toList());
    }

    //dodawanie manager
    @Transactional
    public TrainerResponse addTrainer(TrainerRequest request) {
        var id = UUID.randomUUID();

       // User user = new User(id, request.getUsername(), "{bcrypt}" + Password.hashPassword(request.getPassword()), true);
       // userRepository.save(user);

        //Authority auth = new Authority(request.getUsername());
        //authoritiesRepository.save(auth);

        Trainer trainer = new Trainer();
        trainer.setIdTrainer(id);
        trainer.setName(request.getName());
        trainer.setSurname(request.getSurname());


        long n3 = Math.round(Math.random() * 1000);
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

        long n3 = Math.round(Math.random() * 1000);
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

    @SneakyThrows
    public void addWardToTrainer(String code, String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        TokenPayload tokenPayload = objectMapper.readValue(payload, TokenPayload.class);
        var ward = wardRepository.findByName(tokenPayload.getSub());
        var trainer = trainerRepository.findByCode(code);
        ward.setTrainer(trainer);
        wardRepository.save(ward);
    }


}
