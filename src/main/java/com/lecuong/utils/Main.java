package com.lecuong.utils;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "a");
        map.put(2, "b");

        System.out.println(map.get(2));
    }
}
