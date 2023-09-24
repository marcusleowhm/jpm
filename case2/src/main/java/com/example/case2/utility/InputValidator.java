package com.example.case2.utility;

import com.example.case2.constant.Direction;
import com.example.case2.constant.IssuedCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcus LEOW
 */
@Component
public class InputValidator {

    @Autowired
    private InputUtility inputUtility;

    public boolean isLaunchParameterValid(String[] inputs) {
        //<launch> <starting pos, direction> <issued commands>
        if (inputs.length != 3) {
            return false;
        }
        //starting position should be x,y,direction
        String startingPosition = inputUtility.getStartingPosition(inputs);
        String[] startingCoordinates = startingPosition.split(",");
        if (startingCoordinates.length != 3) {
            return false;
        }

        //Test data type entered for coordinates
        try {
            Integer.parseInt(startingCoordinates[0]); //xPos
            Integer.parseInt(startingCoordinates[1]); //yPos
        } catch (NumberFormatException ex) {
            return false;
        }

        //Test whether initial position is single character
        if (startingCoordinates[2].length() > 1) {
            return false;
        }

        //Test whether initial position is found within Direction enum
        if (Direction.fromCode(startingCoordinates[2].toUpperCase().charAt(0)) == null) {
            return false;
        }

        //Check if the commands entered are single character
        String issuedCommands = inputUtility.getIssuedCommands(inputs);
        String[] commands = issuedCommands.split(",");
        for (String cmd: commands) {
            if (cmd.length() != 1) {
                return false;
            }
        }

        //Check whether the directions entered is valid
        for (String command: commands) {
            if (IssuedCommand.fromCode(command.toUpperCase().charAt(0)) == null) {
                return false;
            }
        }

        return true;
    }

}
