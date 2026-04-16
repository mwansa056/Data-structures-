import java.util.Scanner;
import java.util.Stack;

public class InfixConverter {

    // Check precedence of operators
    static int precedence(char ch) {
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;
        if (ch == '^') return 3;
        return -1;
    }

    // Check if character is operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Convert Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (isOperand(ch)) {
                result += ch;
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            else { // operator
                while (!stack.isEmpty() &&
                       precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse a string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Swap brackets
    static String swapBrackets(String exp) {
        char[] arr = exp.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') arr[i] = ')';
            else if (arr[i] == ')') arr[i] = '(';
        }
        return String.valueOf(arr);
    }

    // Convert Infix to Prefix
    static String infixToPrefix(String exp) {
        String reversed = reverse(exp);
        reversed = swapBrackets(reversed);

        String postfix = infixToPostfix(reversed);

        return reverse(postfix);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter an infix expression: ");
        String expression = input.nextLine();

        String postfix = infixToPostfix(expression);
        String prefix = infixToPrefix(expression);

        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Prefix Expression: " + prefix);

        input.close();
    }
}