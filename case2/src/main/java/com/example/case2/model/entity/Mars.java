package com.example.case2.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Marcus LEOW
 */
@Entity
@Table(name = "mars")
@NoArgsConstructor
@Data
public class Mars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rovers")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rover> rovers;

}
