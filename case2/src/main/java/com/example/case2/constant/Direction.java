package com.example.case2.constant;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('w');

    private static final Map<Character, Direction> directionMap;
    static {
        directionMap = new HashMap<>();
        for (Direction direction: values()) {
            directionMap.put(direction.code, direction);
        }
    }

    private final Character code;
    Direction(Character code) {
        this.code = code;
    }

    public static Direction fromCode(Character code) {
        return directionMap.get(code);
    }

}
