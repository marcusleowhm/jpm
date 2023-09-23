package com.example.case2.controller;

import com.example.case2.model.entity.Rover;
import com.example.case2.model.request.RoverCreationRequest;
import com.example.case2.service.RoverService;
import com.example.case2.utility.MessagePrinter;
import com.example.case2.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * @author Marcus LEOW
 */
@Component
public class UserInterface {

    @Autowired
    private RoverService roverService;

    @Autowired
    private InputValidator inputValidator;

    @Autowired
    private MessagePrinter messagePrinter;

    public void mainLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("─────────────────────────────────────────────────────────────────────────────────");
            System.out.println("Type \"help\" to see the list of commands. Type \"exit\" to exit the program.");
            System.out.print(" > ");

            String line = sc.nextLine();
            String[] inputs = line.trim().replaceAll("\\s{2,}"," ").split(" ");
            String command = inputs[0];

            if (command.equals("exit")) {
                break;
            }

            if (command.equals("launch")) {
                //Multi launch
                if (line.contains("--multi")) {

                    while(true) {
                        int count = 0;
                        System.out.println("Enter starting position, direction and commands <x,y,direction> <f,r,l,b>");
                        System.out.printf();
                        line = sc.nextLine();

                        if (line.equals("commit")) {

                        }

                    }

                }
                //Single launch
                else {
                    if (inputValidator.isSingleLaunchParameterValid(inputs)) {
                        String startingPos = inputs[1];
                        String commands = inputs[2];

                        String[] startCoordinates = startingPos.split(",");
                        Integer xPos = Integer.parseInt(startCoordinates[0]);
                        Integer yPos = Integer.parseInt(startCoordinates[1]);
                        String direction = startCoordinates[2].toUpperCase();

                        RoverCreationRequest request = RoverCreationRequest.builder()
                                .direction(direction.charAt(0))
                                .xPos(xPos)
                                .yPos(yPos)
                                .build();

                        //Check if there is a rover at the starting position
                        Rover result = roverService.createRover(request);

                        ///Move the rover and finalize its position depending on whether there are collisions


                        //Feedback to console
                        System.out.printf("Rover launched, Id: %s%n",  result.getId());
                    }
                }
                continue;
            }

            if (command.equals("list")) {
                List<Rover> rovers = roverService.getRovers();
                if (rovers.isEmpty()) {
                    System.out.println("No rovers launched");
                } else {
                    System.out.println(rovers);
                }
                continue;
            }

            if (command.equals("help")) {
                messagePrinter.printHelp();
                continue;
            }
        }
    }
}
