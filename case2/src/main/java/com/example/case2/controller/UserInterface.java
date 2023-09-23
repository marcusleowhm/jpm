package com.example.case2.controller;

import com.example.case2.model.entity.Rover;
import com.example.case2.model.request.RoverLaunchRequest;
import com.example.case2.service.RoverService;
import com.example.case2.utility.InputUtility;
import com.example.case2.utility.InputValidator;
import com.example.case2.utility.MessagePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private InputUtility inputUtility;

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
            String command = inputUtility.getCommand(inputs);

            if (command.equals("exit")) {
                break;
            }

            if (command.equals("launch")) {
                //Multi launch
                if (line.contains("--multi")) {
                    List<String> userInput = new ArrayList<>();
                    while(true) {
                        for (String s : userInput) {
                            System.out.println(s);
                        }

                        System.out.println("Enter starting position, direction and commands <x,y,direction> <f,r,l,b>");
                        System.out.println("Enter \"commit\" to finalize and launch the rovers. Enter \"cancel\" to abandon launch.");
                        System.out.print(" > ");
                        line = sc.nextLine();
                        userInput.add(line.trim().replaceAll("\\s{2,}"," "));

                        if (line.equals("commit")) {
                            break;
                        }

                        if (line.equals("cancel")) {
                            break;
                        }
                    }

                }
                //Single launch
                else {
                    if (inputValidator.isSingleLaunchParameterValid(inputs)) {
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
                        String response = roverService.launchRover(launchRequest);
                        System.out.println(response);
                    } else {
                        messagePrinter.printInvalidInputMessage();
                    }
                }
                continue;
            }

            if (command.equals("move")) {

                //TODO
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

            messagePrinter.printInvalidInputMessage();
        }
    }
}
