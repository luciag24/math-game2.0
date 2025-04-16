package org.example.services;

import java.util.HashSet;
import java.util.Set;

import org.example.models.MathExample;
import org.springframework.stereotype.Service;

/**
 * Služba na generovanie príkladov pre štvrtý ročník.
 * Obsahuje operácie s vačšími číslami: sčítanie, odčítanie, násobenie a delenie.
 * Pre násobenie a delenie používa len základnú násobilku (čísla do 10).
 * Neobsahuje premenu jednotiek (tá je v samostatnej triede).
 */
@Service
public class FourthGradeMathService implements MathExampleGenerator {

    private final Set<String> generatedQuestions = new HashSet<>();
    
    // Definujeme čísla pre malú násobilku
    private final int[] multiplicationNumbers = {2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Override
    public MathExample generateExample() {
        int number1, number2;
        char operator = randomOperator(new char[]{'+', '-', '*', '/'});

        if (operator == '*' || operator == '/') {
            // Pre násobenie vyberieme obe čísla zo základnej násobilky
            number1 = multiplicationNumbers[(int) (Math.random() * multiplicationNumbers.length)];
            number2 = multiplicationNumbers[(int) (Math.random() * multiplicationNumbers.length)];
            
            if (operator == '/') {
                // Pre delenie použijeme výsledok násobenia ako delenca
                int product = number1 * number2;
                // Pre delenie použijeme jedno z čísel ako deliteľa
                if (Math.random() < 0.5) {
                    number1 = product;  // Príklad bude: (a*b) ÷ b = a
                } else {
                    number1 = product;
                    number2 = number1;  // Príklad bude: (a*b) ÷ a = b
                }
            }
        } else {
            number1 = (int) (Math.random() * 1000 + 1);
            number2 = (int) (Math.random() * 1000 + 1);
        }

        int correctAnswer = calculateAnswer(number1, number2, operator);

        // Ošetrenie záporných výsledkov
        if (operator == '-' && correctAnswer < 0) {
            return generateExample();
        }

        String operatorSymbol = String.valueOf(operator);
        if (operator == '/') {
            operatorSymbol = ":";
        }

        String question = number1 + " " + operatorSymbol + " " + number2;
        
        // Ošetrenie opakovania príkazov
        if (generatedQuestions.contains(question)) {
            return generateExample();
        }
        
        generatedQuestions.add(question);
        return new MathExample(question, correctAnswer);
    }

    private char randomOperator(char[] operators) {
        return operators[(int) (Math.random() * operators.length)];
    }

    private int calculateAnswer(int number1, int number2, char operator) {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> number1 / number2;
            default -> throw new IllegalArgumentException("Neznámy operátor: " + operator);
        };
    }
}




