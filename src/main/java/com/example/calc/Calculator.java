package com.example.calc;

public class Calculator {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return Math.abs(num1 - num2);
    }

    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("분모에 0이 있으면 안됩니다.");
        }
        return Math.round((num1 / num2) * 100) / 100.0;
    }
}
