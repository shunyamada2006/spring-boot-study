package com.example.calculator.controller;

import com.example.calculator.form.CalculatorForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String showForm(){
        return "calculator";
    }
    @PostMapping("/calculator")
    public String calculate(CalculatorForm form,Model model1){
        Integer num1 = form.getNum1();
        Integer num2 = form.getNum2();
        String operator = form.getOperator();
        Integer result =0;
        try{
            switch(operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            model1.addAttribute("output",result);
        }catch(Exception e){
            model1.addAttribute("error","正しく計算できませんでした");
            }
        return "calculator";
    }
}
