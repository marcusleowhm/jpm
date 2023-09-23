package com.example.case2.model.entity;

import com.example.case2.constant.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Marcus LEOW
 */
@Entity
@Table(name = "rovers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x_pos")
    private Integer xPos;

    @Column(name = "y_pos")
    private Integer yPos;

    @Column(name = "direction")
    private Direction direction;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name= "mars_id")
    private Mars mars;

    @Override
    public String toString() {
        return String.format("%n\tid: %s, xPos: %s, yPos: %s, direction: %s, mars: %s%n", id, xPos, yPos, direction, mars.getId());
    }
}
