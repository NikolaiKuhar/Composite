package lt.esdc.text.interpreter;

import lt.esdc.text.interpreter.impl.*;

import java.util.*;

public class ExpressionParser {

    private static final Map<String, Integer> PRECEDENCE = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/");

    public ExpressionInterpreter parse(String input) {
        List<String> tokens = tokenize(input);
        List<String> postfix = toPostfix(tokens);
        return buildExpressionTree(postfix);
    }

    private List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (char c : input.replaceAll("\\s+", "").toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                current.append(c);
            } else {
                if (!current.isEmpty()) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }

        return tokens;
    }

    private List<String> toPostfix(List<String> infix) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String token : infix) {
            if (isNumber(token)) {
                output.add(token);
            } else if (OPERATORS.contains(token)) {
                while (!stack.isEmpty() && isOperator(stack.peek())
                        && PRECEDENCE.get(token) <= PRECEDENCE.get(stack.peek())) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private ExpressionInterpreter buildExpressionTree(List<String> postfix) {
        Deque<ExpressionInterpreter> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(new NumberExpression(Double.parseDouble(token)));
            } else if (OPERATORS.contains(token)) {
                ExpressionInterpreter right = stack.pop();
                ExpressionInterpreter left = stack.pop();
                stack.push(createOperator(token, left, right));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private boolean isNumber(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }

    private ExpressionInterpreter createOperator(String op, ExpressionInterpreter left, ExpressionInterpreter right) {
        return switch (op) {
            case "+" -> new AddExpression(left, right);
            case "-" -> new SubtractExpression(left, right);
            case "*" -> new MultiplyExpression(left, right);
            case "/" -> new DivideExpression(left, right);
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + op);
        };
    }
}
