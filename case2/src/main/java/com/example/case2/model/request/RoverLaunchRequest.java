package com.example.case2.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author Marcus LEOW
 */
@Data
@Builder
public class RoverLaunchRequest {
    private Integer xPos;
    private Integer yPos;
    private Character direction;
    private String issuedCommands;
}
