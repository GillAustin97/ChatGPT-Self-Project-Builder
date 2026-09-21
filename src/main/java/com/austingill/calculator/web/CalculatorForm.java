package com.austingill.calculator.web;

import com.austingill.calculator.domain.Operation;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CalculatorForm {

    @NotNull(message = "Enter the first number.")
    private BigDecimal firstNumber;

    @NotNull(message = "Enter the second number.")
    private BigDecimal secondNumber;

    @NotNull(message = "Choose an operation.")
    private Operation operation = Operation.ADD;

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
