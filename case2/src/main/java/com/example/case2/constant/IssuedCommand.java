package com.example.case2.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcus LEOW
 */
public enum IssuedCommand {

    FORWARD('f'), BACKWARD('b'), LEFT_TURN('l'), RIGHT_TURN('r');

    private static final Map<Character, IssuedCommand> commandMap;

    static {
        commandMap = new HashMap<>();
        for (IssuedCommand cmd: values()) {
            commandMap.put(cmd.code, cmd);
        }
    }

    IssuedCommand(Character code) {
        this.code = code;
    }

    private final Character code;
    public static IssuedCommand fromCode(Character code) {
        return commandMap.get(code);
    }

}
