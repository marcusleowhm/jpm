package com.example.case2.utility;

import org.springframework.stereotype.Component;

/**
 * @author Marcus LEOW
 */
@Component
public class MessagePrinter {

    private static final String HEADER  = "Usage: <command> <parameters>";
    private static final String LAUNCH_MESSAGE = "- Launches a rover onto Mars";
    private static final String LAUNCH_COMMAND = "launch <start position as x,y,direction> <movement commands comma separated>\n";
    private static final String LAUNCH_MULTI_MESSAGE = "- Launches multiple rovers onto Mars at once";
    private static final String LAUNCH_MULTI_COMMAND = "launch --multi\n";
    private static final String ISSUE_MESSAGE = "- Issues command to rover to move";
    private static final String ISSUE_COMMAND = "issue <rover id> <movement commands>\n";
    private static final String LIST_MESSAGE = "- List all the launched rovers and their current positions";
    private static final String LIST_COMMAND = "list\n";
    private static final String INVALID_INPUT = "Invalid inputs entered\n";

    public void printHelp() {
        System.out.println(HEADER);
        System.out.println(LAUNCH_MESSAGE);
        System.out.println(LAUNCH_COMMAND);
        System.out.println(LAUNCH_MULTI_MESSAGE);
        System.out.println(LAUNCH_MULTI_COMMAND);
        System.out.println(ISSUE_MESSAGE);
        System.out.println(ISSUE_COMMAND);
        System.out.println(LIST_MESSAGE);
        System.out.println(LIST_COMMAND);
    }

    public void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT);
    }


}
