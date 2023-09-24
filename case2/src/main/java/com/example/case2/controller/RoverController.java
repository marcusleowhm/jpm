package com.example.case2.controller;

import com.example.case2.model.request.RoverLaunchRequest;
import com.example.case2.model.request.RoverIssueMovementRequest;
import com.example.case2.service.RoverService;
import com.example.case2.utility.InputUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Marcus LEOW
 */
@Controller
public class RoverController {

    @Autowired
    private RoverService roverService;

    @Autowired
    private InputUtility inputUtility;

    public void handleSingleLaunch(String[] inputs) {
        Integer xPos = inputUtility.getXPos(inputs);
        Integer yPos = inputUtility.getYPos(inputs);
        Character direction = inputUtility.getDirection(inputs);
        String movement = inputUtility.getIssuedCommands(inputs);
        RoverLaunchRequest launchRequest = RoverLaunchRequest.builder()
                .xPos(xPos)
                .yPos(yPos)
                .direction(direction)
                .issuedCommands(movement)
                .build();
        System.out.println(roverService.launchRover(launchRequest));
        System.out.println("\n");
    }

    public void handleIssueCommand(String[] inputs) {
        Long roverId = inputUtility.getRoverIdFromIssueCommand(inputs);
        String issuedCommand = inputUtility.getIssuedCommands(inputs);
        RoverIssueMovementRequest movementRequest = RoverIssueMovementRequest.builder()
                .id(roverId)
                .issuedCommands(issuedCommand)
                .build();
        roverService.issueCommandToRover(movementRequest);
    }
}
