package com.example.case2.model.entity;

import javax.persistence.*;

/**
 * @author Marcus LEOW
 */
@Entity
@Table(name = "mars")
public class Mars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
