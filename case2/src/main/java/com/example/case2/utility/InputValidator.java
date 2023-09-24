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
        if (Direction.fromCode(startingCoordinates[2].charAt(0)) == null) {
            return false;
        }

        String[] issuedCommands = inputUtility.getIssuedCommands(inputs).split(",");
        for (String cmd: issuedCommands) {
            //Check if the commands entered are single character
            if (cmd.length() != 1) {
                return false;
            }

            //Check whether the commands entered are valid
            if (IssuedCommand.fromCode(cmd.charAt(0)) == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isIssueCommandParameterValid(String[] inputs) {
        //<issue> <rover_id> <commands>
        if (inputs.length != 3) {
            return false;
        }

        try {
            Integer.parseInt(inputs[1]);
        } catch (NumberFormatException ex) {
            return false;
        }

        String[] issuedCommands = inputUtility.getIssuedCommands(inputs).split(",");
        for (String cmd: issuedCommands) {
            //Check if the commands entered are single character
            if (cmd.length() != 1) {
                return false;
            }

            //Check whether the commands entered are valid
            if (IssuedCommand.fromCode(cmd.charAt(0)) == null) {
                return false;
            }
        }

        return true;
    }

}
