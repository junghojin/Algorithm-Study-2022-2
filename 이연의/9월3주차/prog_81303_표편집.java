import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    
    static class Cell{
        int prev, next, origin;
        public Cell(int p ,int o ,int n){
            this.prev = p;
            this.origin = o;
            this.next = n;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];

        // 셀의 연결 정보
        for(int i = 0 ; i<n;i++){
            prev[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;

        Stack<Cell> del = new Stack<>();
        for(int i = 0 ; i < cmd.length; i++){
            StringTokenizer st = new StringTokenizer(cmd[i]," ");
            char CMD = st.nextToken().toCharArray()[0];
            if(CMD=='U') {
                int num = Integer.parseInt(st.nextToken());
                while(num-->0) k = prev[k];
            }
            else if(CMD=='D'){
                int num = Integer.parseInt(st.nextToken());
                while(num-->0) k = next[k];
            }else if(CMD=='C'){
                del.push(new Cell(prev[k], k , next[k]));

                if(next[k]!=-1) prev[next[k]] = prev[k];
                if(prev[k]!=-1) next[prev[k]] = next[k];

                if(next[k]== -1) k =prev[k];
                else k = next[k];
            }else{
                Cell back = del.pop();
                if(back.prev != -1) next[back.prev] = back.origin;
                if(back.next != -1 )prev[back.next] = back.origin;
            }
            
        }
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while(!del.empty()){
            sb.setCharAt(del.pop().origin, 'X');
        }
        return sb.toString();
    }
    
}
