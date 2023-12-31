package com.example.case2.service;

import com.example.case2.model.entity.Mars;
import com.example.case2.model.entity.Rover;
import com.example.case2.repository.MarsRepository;
import com.example.case2.utility.MessagePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Marcus LEOW
 */
@Service
public class MarsService {

    @Autowired
    private MarsRepository marsRepository;

    @Autowired
    private MessagePrinter messagePrinter;

    public Boolean marsExists() {
        List<Mars> mars = marsRepository.findAll();
        return !mars.isEmpty();
    }

    @Transactional
    public void createMars() {
        if (marsExists()) {
            return;
        }
        Mars newMars = new Mars();
        marsRepository.save(newMars);
        messagePrinter.printMarsCreationMessage();
    }

    public Mars getMars() {
        List<Mars> mars = marsRepository.findAll();
        return mars.get(0); //There will only be one Mars
    }

    public boolean coordinatesAlreadyHasRover(Integer xPos, Integer yPos) {
        Mars existingMars = getMars();
        for (Rover rover: existingMars.getRovers()) {
            if (rover.getXPos().equals(xPos) && rover.getYPos().equals(yPos)) {
                return true;
            }
        }
        return false;
    }
}
