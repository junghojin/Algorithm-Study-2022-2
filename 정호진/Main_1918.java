import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

// 22. 07. 15. 금 - 백준 1918 - 골드 2 - 후위표기식 (차량기지 알고리즘 - Stack)

public class Main_1918 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine().trim();

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        HashMap<Character, Integer> priority = new HashMap<>(); // 수의 크기가 클수록 우선순위가 높은 것
        priority.put('(', 0);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('+', 1);
        priority.put('-', 1);

        for(int i = 0, length = expression.length(); i < length; i++) {
            char current = expression.charAt(i);

            if(current >= 'A' && current <= 'Z') { // 피연산자인 경우
                postfix.append(current);
            } else if(current == '(') { // 여는 괄호인 경우
                stack.push(current);
            } else if (current == ')') { // 닫는 괄호인 경우
                while(!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // '(' 제거
            } else { // 연산자인 경우
                int current_priority = priority.get(current);

                while(!stack.isEmpty() && priority.get(stack.peek()) >= current_priority) {
                    postfix.append(stack.pop());
                }
                stack.push(current);
            }
        }
        while(!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        System.out.println(postfix.toString());
    }
}
