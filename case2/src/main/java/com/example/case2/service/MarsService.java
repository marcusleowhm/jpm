package com.example.case2.service;

import com.example.case2.model.entity.Mars;
import com.example.case2.model.entity.Rover;
import com.example.case2.model.request.RoverLaunchRequest;
import com.example.case2.repository.MarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcus LEOW
 */
@Service
public class MarsService {

    @Autowired
    private MarsRepository marsRepository;

    public Boolean marsExists() {
        List<Mars> mars = marsRepository.findAll();
        return !mars.isEmpty();
    }

    public String createMars() {
        if (marsExists()) {
            return "Mars already exists";
        }
        Mars newMars = new Mars();
        marsRepository.save(newMars);
        return String.format("Let there be Mars - id: %s\n", newMars.getId());
    }

    public Mars getMars() {
        Optional<Mars> optionalMars = marsRepository.findById(1L);
        return optionalMars.orElse(null);
    }

    public boolean coordinatesAlreadyHasRover(RoverLaunchRequest request) {
        Mars existingMars = getMars();
        for (Rover rover: existingMars.getRovers()) {
            if (rover.getXPos().equals(request.getXPos()) && rover.getYPos().equals(request.getYPos())) {
                return true;
            }
        }
        return false;
    }
}
