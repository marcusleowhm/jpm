package com.example.case2.repository;

import com.example.case2.model.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus LEOW
 */
@Repository
public interface RoverRepository extends JpaRepository<Rover, Long> {
}
