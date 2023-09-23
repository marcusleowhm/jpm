package com.example.case2.validator;

import com.example.case2.constant.Direction;
import org.springframework.stereotype.Component;

/**
 * @author Marcus LEOW
 */
@Component
public class InputValidator {

    public boolean isSingleLaunchParameterValid(String[] inputs) {
        //<launch> <starting pos, direction> <issued commands>
        if (inputs.length != 3) {
            return false;
        }

        //starting position should be x,y,direction
        String[] startingPos = inputs[1].split(",");
        if (startingPos.length != 3) {
            return false;
        }

        //Test data type entered for coordinates
        try {
            Integer.parseInt(startingPos[0]); //xPos
            Integer.parseInt(startingPos[1]); //yPos
        } catch (NumberFormatException ex) {
            return false;
        }

        //Test whether initial position is single character
        if (startingPos[2].length() > 1) {
            return false;
        }

        //Test whether initial position is found within Direction enum
        if (Direction.fromCode(startingPos[2].toUpperCase().charAt(0)) == null) {
            return false;
        }

        return true;
    }

    public boolean isMultiLaunchParameterValid(String[] inputs) {
        return true;
    }

}
