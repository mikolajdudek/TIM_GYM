package com.example.gym.controller;

import com.example.gym.dto.WardDetailsResponse;
import com.example.gym.dto.WardRequest;
import com.example.gym.dto.WardResponse;
import com.example.gym.services.WardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class WardController {
    private final WardService wardsService;

    //pobieranie all pracowników
    @GetMapping("/api/wards")
    public List<WardResponse> getAll() {
        return wardsService.getAll();
    }

    @GetMapping("/api/wards/{name}")
    public WardDetailsResponse getWardsByName(@PathVariable String name) {
        return wardsService.getByName(name);
    }

    //dodawnie pracowników
    @PostMapping("/api/wards")
    public ResponseEntity addWards(@RequestBody WardRequest wardsRequest) {
        wardsService.addWards(wardsRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //usuwanie pracowników
    @DeleteMapping("/api/wards/{name}")
    public void deleteWards(@PathVariable String name) {
        wardsService.deleteWards(name);
    }

    //edycja pracownika
    @PutMapping("/api/wards/{name}")
    public void editWards(@PathVariable String name, @RequestBody WardRequest wardsRequest) {
        wardsService.editWards(name, wardsRequest);
    }
}
