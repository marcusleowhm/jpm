package com.example.case2.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Direction {
    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');

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
