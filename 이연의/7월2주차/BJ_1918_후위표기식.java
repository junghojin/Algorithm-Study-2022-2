package Baekjoon;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BJ_1918_후위표기식 {

    public static void main(String[] args) throws IOException {
        Scanner sc  = new Scanner(System.in);
        String input = sc.nextLine();

        String ans = "";
        Stack<Character> stack = new Stack<>();

        for(int i =0 ; i<input.length(); i++){
            char c = input.charAt(i);
            if(c == '('){
                stack.push('(');
            }else if(c == '+' || c == '-'||c == '*'||c == '/'){
                if(!stack.empty()){
                    while(priority(stack.peek()) >= priority(c)){
                        ans += stack.pop();
                        if(stack.empty()) break;
                    }
                }
                stack.push(c);
            }else if(c==')'){
                while(stack.peek()!='('){
                    ans += stack.pop();
                }
                stack.pop();
            }else{
                ans+=c;
            }

        }
        while(!stack.empty()){
            ans += stack.pop();
        }
        System.out.println(ans);
    }

    static int priority(char c){
        int result;
        switch (c){
            case '+': case '-' :
                result = 2; break;
            case '*': case '/' :
                result = 3; break;
            default:
                result = 1; break;

        }
        return result;
    }

}
