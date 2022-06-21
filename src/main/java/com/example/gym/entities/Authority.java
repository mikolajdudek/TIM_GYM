package com.example.gym.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "authorities")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Authority {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private final String enabled = "USER";
}
