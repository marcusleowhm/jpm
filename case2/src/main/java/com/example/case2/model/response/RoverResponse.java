package com.example.case2.model.response;

import com.example.case2.constant.Direction;
import lombok.Builder;
import lombok.Data;

/**
 * @author Marcus LEOW
 */
@Builder
@Data
public class RoverResponse {

    private Integer xPos;
    private Integer yPos;
    private Direction direction;

}
