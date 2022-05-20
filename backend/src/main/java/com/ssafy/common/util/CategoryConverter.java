package com.ssafy.common.util;

public class CategoryConverter {

    public static int convertToInt(String category) {
        if ("축구".equals(category)) {
            return 1;
        } else if ("풋살".equals(category)) {
            return 2;
        } else if ("농구".equals(category)) {
            return 3;
        } else if ("야구".equals(category)) {
            return 4;
        } else if ("볼링".equals(category)) {
            return 5;
        } else if ("골프".equals(category)) {
            return 6;
        } else if ("테니스".equals(category)) {
            return 7;
        } else if ("배드민턴".equals(category)) {
            return 8;
        } else if ("헬스".equals(category)) {
            return 9;
        } else if ("필라테스".equals(category)) {
            return 10;
        } else if ("격투기".equals(category)) {
            return 11;
        } else if ("수영".equals(category)) {
            return 12;
        }
        return 0;
    }

}
