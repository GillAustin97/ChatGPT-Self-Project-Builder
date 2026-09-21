package com.austingill.calculator.service;

import com.austingill.calculator.domain.Operation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class CalculatorService {

    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber, Operation operation) {
        return switch (operation) {
            case ADD -> firstNumber.add(secondNumber);
            case SUBTRACT -> firstNumber.subtract(secondNumber);
            case MULTIPLY -> firstNumber.multiply(secondNumber);
            case DIVIDE -> divide(firstNumber, secondNumber);
        };
    }

    private BigDecimal divide(BigDecimal firstNumber, BigDecimal secondNumber) {
        if (secondNumber.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("You cannot divide by zero.");
        }

        return firstNumber.divide(secondNumber, MathContext.DECIMAL64);
    }
}
