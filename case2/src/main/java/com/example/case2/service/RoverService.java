package com.example.case2.service;

import com.example.case2.constant.Direction;
import com.example.case2.model.entity.Rover;
import com.example.case2.model.request.RoverLaunchRequest;
import com.example.case2.model.request.RoverMovementRequest;
import com.example.case2.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcus LEOW
 */
@Service
public class RoverService {

    @Autowired
    private RoverRepository roverRepository;

    @Autowired
    private MarsService marsService;

    public String launchRover(RoverLaunchRequest request) {
        //Create Mars if not exist
        if (!marsService.marsExists()) {
            System.out.println("No Mars, creating one...");
            String result = marsService.createMars();
            System.out.println(result);
        }

        //Check if there is a rover at the starting position
        if (marsService.coordinatesAlreadyHasRover(request)) {
            return "Starting coordinates already has a rover, aborting launch...";
        }

        //Create rover if no collision at initial position
        Rover createdRover = createRover(request);

        ///Move the rover and finalize its position depending on whether there are collisions

        //Feedback to console
//                        System.out.printf("Rover launched, Id: %s%n",  result.getId());


        return "";
    }

    @Transactional
    private Rover createRover(RoverLaunchRequest request) {
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
        Optional<Rover> optionalRover = roverRepository.findById(request.getId());
        if (!optionalRover.isPresent()) {
            return;
        }

        Rover rover = optionalRover.get();
        Integer currentX = rover.getXPos();
        Integer currentY = rover.getYPos();
        String[] issuedCommands = request.getIssuedCommands().split(",");
        for (String issuedCommand: issuedCommands) {

        }
    }

}
