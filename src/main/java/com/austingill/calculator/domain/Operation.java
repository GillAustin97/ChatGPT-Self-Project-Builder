package com.austingill.calculator.domain;

public enum Operation {
    ADD("Addition", "+"),
    SUBTRACT("Subtraction", "−"),
    MULTIPLY("Multiplication", "×"),
    DIVIDE("Division", "÷");

    private final String label;
    private final String symbol;

    Operation(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getSymbol() {
        return symbol;
    }
}
