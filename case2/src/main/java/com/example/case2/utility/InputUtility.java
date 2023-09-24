package com.example.case2.utility;

import org.springframework.stereotype.Component;

/**
 * @author Marcus LEOW
 */
@Component
public class InputUtility {

    public String getCommand(String[] inputs) {
        return inputs[0];
    }

    public String getStartingPosition(String[] inputs) {
        return inputs[1];
    }

    public Long getRoverIdFromIssueCommand(String[] inputs) {
        return Long.parseLong(inputs[1]);
    }

    public String getIssuedCommands(String[] inputs) {
        return inputs[2];
    }

    public Integer getXPos(String[] inputs) {
        String startingPosition = getStartingPosition(inputs);
        String[] startCoordinates = startingPosition.split(",");
        return Integer.parseInt(startCoordinates[0]);
    }

    public Integer getYPos(String[] inputs) {
        String startingPosition = getStartingPosition(inputs);
        String[] startCoordinates = startingPosition.split(",");
        return Integer.parseInt(startCoordinates[1]);
    }

    public Character getDirection(String[] inputs) {
        String startingPosition = getStartingPosition(inputs);
        String[] startCoordinates = startingPosition.split(",");
        return startCoordinates[2].charAt(0);
    }


}
