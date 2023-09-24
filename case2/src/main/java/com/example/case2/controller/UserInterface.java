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
            messagePrinter.printMainPrompt();
            String line = sc.nextLine();
            String[] inputs = line.trim().replaceAll("\\s{2,}"," ").split(" ");
            String command = inputUtility.getCommand(inputs);

            //Exit command
            if (command.equals("exit")) {
                break;
            }

            //Launch command
            if (command.equals("launch")) {
                //Multi launch
                if (line.contains("--multi")) {
                    getMultiLaunchInputs();
                }
                //Single launch
                else {
                    if (!inputValidator.isLaunchParameterValid(inputs)) {
                        messagePrinter.printInvalidInputMessage();
                        continue;
                    }
                    handleSingleLaunch(inputs);
                }
                continue;
            }

            if (command.equals("issue")) {
                if (!inputValidator.isValidMovementParameters(inputs)) {
                    messagePrinter.printInvalidInputMessage();
                    continue;
                }
                handleIssueCommand(inputs);
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

    public void handleIssueCommand(String[] inputs) {
        //TODO
    }

    public void handleSingleLaunch(String[] inputs) {
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
        System.out.println(roverService.launchRover(launchRequest));
    }

    public void getMultiLaunchInputs() {
        Scanner sc = new Scanner(System.in);
        List<String> userInput = new ArrayList<>();
        while(true) {
            for (int i = 0; i < userInput.size(); i++) {
                String[] inputCommands = userInput.get(i).split(" ");
                System.out.printf("Rover %s: <%s> <%s>%n", i + 1, inputCommands[1], inputCommands[2]);
            }
            messagePrinter.printMultiLaunchPrompt();
            String line = sc.nextLine();

            if (line.equals("commit")) {
                for (String input: userInput) {
                    handleSingleLaunch(input.split(" "));
                }
                break;
            }

            if (line.equals("cancel")) {
                break;
            }

            //Modify line and add a parameter to remain compatible with validity checker
            line = "launch " + line;

            //check each user input for validity before collecting it in ArrayList
            String[] inputs = line.split(" ");
            if (!inputValidator.isLaunchParameterValid(inputs)) {
                messagePrinter.printInvalidInputMessage();
                continue;
            }
            userInput.add(line.trim().replaceAll("\\s{2,}"," "));
        }
    }
}
