package ru.vsu.diplom.service.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class OperatorUtils {
    private static final Map <String, BinaryOperator <Boolean>> operations = new HashMap();

    static {
        operations.put("OR", (a, b) -> a || b);
        operations.put("AND", (a, b) -> a && b);
    }

    public static BinaryOperator <Boolean> getOperator(String name) {
        return name != null && operations.get(name) != null ?
                operations.get(name) : operations.get("OR");

    }
}
