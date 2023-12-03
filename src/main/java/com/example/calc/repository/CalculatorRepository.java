package com.example.calc.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CalculatorRepository {
    List<Object> operateHistory = new ArrayList<>();

    public void addResult(Object result) {
        this.operateHistory.add(result);
    }
}
