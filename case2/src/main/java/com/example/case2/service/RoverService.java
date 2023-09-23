package com.example.case2.service;

import com.example.case2.constant.Direction;
import com.example.case2.model.entity.Rover;
import com.example.case2.model.request.RoverCreationRequest;
import com.example.case2.model.request.RoverMovementRequest;
import com.example.case2.model.response.RoverResponse;
import com.example.case2.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus LEOW
 */
@Service
public class RoverService {

    @Autowired
    private RoverRepository roverRepository;

    public Rover createRover(RoverCreationRequest request) {
        Rover rover = new Rover();
        rover.setXPos(request.getXPos());
        rover.setYPos(request.getYPos());
        rover.setDirection(Direction.fromCode(request.getDirection()));
        return roverRepository.save(rover);
    }

    public List<Rover> getRovers() {
        return roverRepository.findAll();
    }

    public void moveRover(RoverMovementRequest request) {

    }

}
