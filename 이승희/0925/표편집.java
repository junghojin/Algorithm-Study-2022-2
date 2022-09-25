// 2021 카카오 채용연계형 인턴십 - 표편집
// 우선순위 큐 + 스택
import java.util.PriorityQueue;
import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
       
        PriorityQueue<Integer> up = new PriorityQueue<Integer>();
        PriorityQueue<Integer> down = new PriorityQueue<Integer>();
        Stack<Integer> delete = new Stack<Integer>();
        for(int i=0;i<k;i++){
            up.offer(-i);
        }
        
        for(int i=k;i<n;i++){
            down.offer(i);
        }
        
        for(String c:cmd){
            String[] data = c.split(" ");
            if(data[0].equals("U")){
                int move = Integer.parseInt(data[1]);
                for(int i=0;i<move;i++){
                    if(!up.isEmpty()){
                        down.offer(-up.poll());
                    }
                }
            } else if(data[0].equals("D")){
                int move = Integer.parseInt(data[1]);
                for(int i=0;i<move;i++){
                    if(!down.isEmpty()){
                        up.offer(-down.poll());
                    }
                }
            } else if(data[0].equals("C")){
                delete.push(down.poll());
                if(down.isEmpty()){
                    down.offer(-up.poll());
                }
            } else if(data[0].equals("Z")){
                int number = delete.pop();
                if(down.peek() > number){
                    up.offer(-number);
                } else {
                    down.offer(number);
                }
            }
        }
        
        StringBuffer sb = new StringBuffer("O".repeat(n));
        while(!delete.isEmpty()){
            sb.setCharAt(delete.pop(), 'X');
        }
        
        return sb.toString();
    }
}
