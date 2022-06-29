package com.example.gym.controller;

import com.example.gym.dto.TrainerRequest;
import com.example.gym.dto.TrainerResponse;
import com.example.gym.services.TrainerService;
import com.example.gym.utils.TokenPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    //pobieranie
    @GetMapping("/api/trainers")
    public ResponseEntity<List<TrainerResponse>> getAll(){
        return ResponseEntity.ok().body(trainerService.getAll());
    }


    //dodawnie
    @PostMapping("/api/trainers")
    public ResponseEntity<TrainerResponse> addTrainer(@RequestBody TrainerRequest trainerRequest) {
        return ResponseEntity.ok().body(trainerService.addTrainer(trainerRequest));
    }

    @GetMapping("/api/trainers/{code}")
    public ResponseEntity<TrainerResponse> getTrainer(@PathVariable String code){
        return ResponseEntity.ok().body(trainerService.getTrainer(code));
    }

    //usuwanie
    @DeleteMapping("/api/trainers/{code}")
    public ResponseEntity<String> deleteTrainer(@PathVariable String code){
        trainerService.deleteTrainer(code);
        return ResponseEntity.ok().body("Usunięto");
    }

    //edycja
    @PutMapping("/api/trainers/{code}")
    public ResponseEntity<TrainerResponse> editTrainer(@PathVariable String code, @RequestBody TrainerRequest request){
        return ResponseEntity.ok().body(trainerService.editTrainer(code, request));
    }

    @PostMapping("/api/trainers/{code}/addWard")
    public void addWard(@PathVariable String code, HttpServletRequest request){
        var token = request.getHeader("Authorization");
        trainerService.addWardToTrainer(code, token);
    }
}

