package com.example.case2.service;

import com.example.case2.constant.Direction;
import com.example.case2.constant.IssuedCommand;
import com.example.case2.model.entity.Mars;
import com.example.case2.model.entity.Rover;
import com.example.case2.model.request.RoverLaunchRequest;
import com.example.case2.model.request.RoverIssueMovementRequest;
import com.example.case2.repository.RoverRepository;
import com.example.case2.utility.MessagePrinter;
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
    private MessagePrinter messagePrinter;

    @Autowired
    private RoverRepository roverRepository;

    @Autowired
    private MarsService marsService;

    public String launchRover(RoverLaunchRequest launchRequest) {

        //Create Mars if not exist
        if (!marsService.marsExists()) {
            messagePrinter.printNoMarsFoundMessage();
            marsService.createMars();
        }

        //Check if there is a rover at the starting position
        if (marsService.coordinatesAlreadyHasRover(launchRequest.getXPos(), launchRequest.getYPos())) {
            return "Starting coordinates already has a rover, aborting launch...";
        }

        //Create rover if no collision at initial position
        Rover createdRover = createRover(launchRequest);

        ///Move the rover and finalize its position depending on whether there are collisions
        RoverIssueMovementRequest movementRequest = RoverIssueMovementRequest.builder()
                .id(createdRover.getId())
                .issuedCommands(launchRequest.getIssuedCommands())
                .build();
        issueCommandToRover(movementRequest);

        Rover updatedRover = getRoverById(createdRover.getId());
        if (updatedRover != null) {
            return String.format("Rover launched, Id: %s%n", updatedRover.getId()) +
                    String.format("Final Coordinate: %s, %s%n", updatedRover.getXPos(), updatedRover.getYPos()) +
                    String.format("Final Direction: %s", updatedRover.getDirection());
        }
        return "Rover cannot be found";
    }

    @Transactional
    private Rover createRover(RoverLaunchRequest request) {
        Mars existingMars = marsService.getMars();
        Rover rover = new Rover();
        rover.setXPos(request.getXPos());
        rover.setYPos(request.getYPos());
        rover.setDirection(Direction.fromCode(request.getDirection()));
        rover.setMars(existingMars);
        return roverRepository.save(rover);
    }

    public Rover getRoverById(Long id) {
        Optional<Rover> optionalRover = roverRepository.findById(id);
        return optionalRover.orElse(null);
    }

    public List<Rover> getRovers() {
        return roverRepository.findAll();
    }

    @Transactional
    public void issueCommandToRover(RoverIssueMovementRequest request) {
        Optional<Rover> optionalRover = roverRepository.findById(request.getId());
        if (!optionalRover.isPresent()) {
            return;
        }

        Rover rover = optionalRover.get();
        String[] commandStrings = request.getIssuedCommands().split(",");

        for (String commandString: commandStrings) {
            IssuedCommand command = IssuedCommand.fromCode(commandString.charAt(0));
            moveRover(command, rover);
        }
        roverRepository.save(rover);
    }

    private void moveRover(IssuedCommand issuedCommand, Rover rover) {
        if (issuedCommand == IssuedCommand.FORWARD) {
            moveForward(rover);
            return;
        }
        if (issuedCommand == IssuedCommand.BACKWARD) {
            moveBackward(rover);
            return;
        }
        if (issuedCommand == IssuedCommand.LEFT_TURN) {
            rotateLeft(rover);
            return;
        }
        if (issuedCommand == IssuedCommand.RIGHT_TURN) {
            rotateRight(rover);
        }
    }

    //Move the rover one coordinate in the direction it is facing
    private void moveForward(Rover rover) {
        switch (rover.getDirection()) {
            case NORTH:
                movePositiveY(rover);
                break;
            case SOUTH:
                moveNegativeY(rover);
                break;
            case EAST:
                movePositiveX(rover);
                break;
            case WEST:
                moveNegativeX(rover);
                break;
        }
    }

    //Move the rover one coordinate back in the direction it is facing
    private void moveBackward(Rover rover) {
        switch (rover.getDirection()) {
            case NORTH:
                moveNegativeY(rover);
                break;
            case SOUTH:
                movePositiveY(rover);
                break;
            case EAST:
                moveNegativeX(rover);
                break;
            case WEST:
                movePositiveX(rover);
                break;
        }
    }

    private void rotateLeft(Rover rover) {
        switch(rover.getDirection()) {
            case NORTH:
                rover.setDirection(Direction.WEST);
                break;
            case SOUTH:
                rover.setDirection(Direction.EAST);
                break;
            case EAST:
                rover.setDirection(Direction.NORTH);
                break;
            case WEST:
                rover.setDirection(Direction.SOUTH);
                break;
        }
    }

    private void rotateRight(Rover rover) {
        switch (rover.getDirection()) {
            case NORTH:
                rover.setDirection(Direction.EAST);
                break;
            case SOUTH:
                rover.setDirection(Direction.WEST);
                break;
            case EAST:
                rover.setDirection(Direction.SOUTH);
                break;
            case WEST:
                rover.setDirection(Direction.NORTH);
                break;
        }
    }

    private void moveNegativeX(Rover rover) {
        Integer nextXPos = rover.getXPos() - 1;
        if (marsService.coordinatesAlreadyHasRover(nextXPos, rover.getYPos())) {
            return;
        }
        rover.setXPos(nextXPos);
    }

    private void movePositiveX(Rover rover) {
        Integer nextXPos = rover.getXPos() + 1;
        if (marsService.coordinatesAlreadyHasRover(nextXPos, rover.getYPos())) {
            return;
        }
        rover.setXPos(nextXPos);
    }

    private void moveNegativeY(Rover rover) {
        Integer nextYPos = rover.getYPos() - 1;
        if (marsService.coordinatesAlreadyHasRover(rover.getXPos(), nextYPos)) {
            return;
        }
        rover.setYPos(nextYPos);
    }

    private void movePositiveY(Rover rover) {
        Integer nextYPos = rover.getYPos() + 1;
        if (marsService.coordinatesAlreadyHasRover(rover.getXPos(), nextYPos)) {
            return;
        }
        rover.setYPos(nextYPos);
    }
}
