package lt.esdc.text.interpreter.impl;

import lt.esdc.text.interpreter.ExpressionInterpreter;

import java.util.*;

public class MathExpressionInterpreter implements ExpressionInterpreter {

    private static final Map<String, Integer> PRIORITY = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    @Override
    public int interpret(String expression) {
        List<String> postfix = toPostfix(expression);
        return evaluate(postfix);
    }

    private List<String> toPostfix(String expression) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/() ", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.matches("\\d+")) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!stack.isEmpty() &&
                        isOperator(stack.peek()) &&
                        PRIORITY.get(token) <= PRIORITY.get(stack.peek())) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop(); // удаляем "("
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private int evaluate(List<String> postfix) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : postfix) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return PRIORITY.containsKey(token);
    }
}
