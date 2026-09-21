package com.austingill.calculator.web;

import com.austingill.calculator.domain.Operation;
import com.austingill.calculator.service.CalculatorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @ModelAttribute("operations")
    public Operation[] operations() {
        return Operation.values();
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("calculator", new CalculatorForm());
        return "calculator";
    }

    @PostMapping("/")
    public String calculate(
            @Valid @ModelAttribute("calculator") CalculatorForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "calculator";
        }

        try {
            BigDecimal result = calculatorService.calculate(
                    form.getFirstNumber(),
                    form.getSecondNumber(),
                    form.getOperation()
            );

            model.addAttribute("expression", buildExpression(form));
            model.addAttribute("result", format(result));
        } catch (ArithmeticException exception) {
            bindingResult.rejectValue("secondNumber", "division.by.zero", exception.getMessage());
        }

        return "calculator";
    }

    private String buildExpression(CalculatorForm form) {
        return "%s %s %s".formatted(
                format(form.getFirstNumber()),
                form.getOperation().getSymbol(),
                format(form.getSecondNumber())
        );
    }

    private String format(BigDecimal number) {
        BigDecimal normalized = number.stripTrailingZeros();
        return normalized.compareTo(BigDecimal.ZERO) == 0 ? "0" : normalized.toPlainString();
    }
}
