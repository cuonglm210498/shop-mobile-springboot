package com.lecuong.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);


        System.out.println(localDate.plusDays(3));

        SimpleDateFormat dateFormat;
        String text = "Nam Định, ngày " + localDate.getDayOfMonth() + ", tháng " + localDate.getMonth().getValue() + ", năm " +localDate.getYear();

        System.out.println("Nam Định, ngày " + new SimpleDateFormat("dd").format(new Date()) + ", tháng " + new SimpleDateFormat("MM").format(new Date()) + ", năm " + new SimpleDateFormat("yyyy").format(new Date()));
    }
}
