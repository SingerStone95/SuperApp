package com.singerstone.jojo;

public class 有效括号 {

    public static void main(String[] args) {
        System.out.println(new 有效括号().isValid("((({{}}[]))"));
    }

    //'('，')'，'{'，'}'，'['，']'
    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '('){
                stack.push(')');
            }else if (c == '{'){
                stack.push('}');
            }else if (c == '['){
                stack.push(']');
            }
            else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
