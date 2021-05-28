package com.watercup.concurrency;

import java.util.Stack;

class Calculator {
    int i = 0;
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';

        while (i < s.length()) {
            char c = s.charAt(i++);
            System.out.println("i: " + i + ", c: " + c + ", sign: " + sign);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }

            if (c == '(') {
                num = calculate(s);
                System.out.println("return from (): " + num);
            }

            if ( ("+-*/".indexOf(c) != -1) || i == s.length() || c == ')') {
                int pre = 0;
                switch(sign) {
                    case '+':
                        stack.push(num);
                        System.out.println("push: " + num);
                        break;
                    case '-':
                        System.out.println("push: " + -num);
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        System.out.println("push: " + pre * num);
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        System.out.println("push: " + pre * num);
                        stack.push( pre / num);
                        break;
                }

                sign = c;
                num = 0;
            }

            if (c == ')') {
                break;
            }
        }

        int sum = 0;
        for (int n : stack) sum += n;
        return sum;
    }
}
