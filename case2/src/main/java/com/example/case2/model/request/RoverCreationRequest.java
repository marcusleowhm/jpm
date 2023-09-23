package com.example.case2.model.request;

import com.example.case2.constant.Direction;
import lombok.Builder;
import lombok.Data;

/**
 * @author Marcus LEOW
 */
@Data
@Builder
public class RoverCreationRequest {

    private Integer xPos;
    private Integer yPos;
    private Character direction;
}
