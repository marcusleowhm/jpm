package com.example.case2.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author Marcus LEOW
 */
@Builder
@Data
public class RoverIssueMovementRequest {
    private Long id;
    private String issuedCommands;
}
