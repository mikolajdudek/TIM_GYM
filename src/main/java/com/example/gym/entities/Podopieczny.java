package com.example.gym.entities;


import javax.persistence.*;

public class Podopieczny {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_podopieczny")
    private Integer idPodopieczny;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trener")
    private Trener trener;
}
