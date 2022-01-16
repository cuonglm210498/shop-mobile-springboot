package com.lecuong.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);


        System.out.println(localDate.plusDays(3));
    }
}
