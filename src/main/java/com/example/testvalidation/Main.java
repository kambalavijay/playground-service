package com.example.testvalidation;

import java.util.Map;

public class Main {
    private String prop;

    public static void main(String[] args) {

        Map<String, String> map = Map.of("key1", "value1");
        String s = map.get("key2");
        System.out.println(s);
        System.out.println("value2".equals(s));

    }

    public Main(String var) {
        this.prop = getv1(var);
    }

    private String getv1(String var) {
        return var+"1";
    }
}
