package com.example.case2.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Marcus LEOW
 */
@Entity
@Table(name = "rovers")
public class Rover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
