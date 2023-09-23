package com.example.case2.utility;

import org.springframework.stereotype.Component;

/**
 * @author Marcus LEOW
 */
@Component
public class MessagePrinter {

    private static final String HEADER  = "Usage: <command> <parameters>";
    private static final String LAUNCH_MESSAGE = "- Launch a rover onto Mars";
    private static final String LAUNCH_COMMAND = "launch <start position as x,y,direction> <movement commands comma separated>\n";
    private static final String LAUNCH_MULTI_MESSAGE = "- Launch multiple rovers onto Mars at once";
    private static final String LAUNCH_MULTI_COMMAND = "launch --multi\n";

    public void printHelp() {
        System.out.println(HEADER);
        System.out.println(LAUNCH_MESSAGE);
        System.out.println(LAUNCH_COMMAND);
        System.out.println(LAUNCH_MULTI_MESSAGE);
        System.out.println(LAUNCH_MULTI_COMMAND);

    }


}
