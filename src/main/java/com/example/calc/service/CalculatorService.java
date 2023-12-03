package com.example.calc.service;

import com.example.calc.repository.CalculatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;

    public int add(int num1, int num2) {
        int result = num1 + num2;
        calculatorRepository.addResult(result);
        return result;
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
