package BJ;

import java.util.*;

public class BJ1918 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();


        for(char c:s){
            if (c>=65) sb.append(c);
            else {
                if (c=='+'||c=='-'){
                    while(!stack.isEmpty()&&stack.peek() !='(') {
                        sb.append(stack.pop());
                    }
                    stack.add(c);
                }else if (c=='*'||c=='/'){
                    while(!stack.isEmpty()&&(stack.peek()=='*'||stack.peek()=='/')) {
                        sb.append(stack.pop());
                    }
                    stack.add(c);
                }else if (c==')'){
                    while(stack.peek()!='('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                } else {
                    stack.add(c);
                }
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.print(sb);
    }

}
