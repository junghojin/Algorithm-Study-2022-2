package kakao;

import java.util.Stack;

public class kakao_81303 {

    public static void main(String[] args) {
        // 입력 값
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};


        StringBuilder sb = new StringBuilder();

        int[] pre = new int[n];
        int[] post = new int[n];

        for (int i = 0; i < n; i++) {
            pre[i]=i-1;
            post[i] = i+1;
        }

        sb.append("O".repeat(n));

        Stack<Integer> stack = new Stack<>();

        for(String s:cmd){
            String[] cArr = s.split(" ");
            char c = cArr[0].charAt(0);
            if (c=='C'){
                stack.add(k);
                sb.setCharAt(k,'X');
                if(pre[k]>=0) post[pre[k]]=post[k];
                if(post[k]<n) pre[post[k]]=pre[k];
                if(post[k]==n) k=pre[k];
                else k=post[k];
            } else if(c=='D'){
                int cnt = Integer.parseInt(cArr[1]);
                while(cnt-->0) k=post[k];
            } else if (c=='U'){
                int cnt = Integer.parseInt(cArr[1]);
                while(cnt-->0) k=pre[k];
            } else if(c=='Z'){
                int zIdx = stack.pop();

                if(post[zIdx]<n) pre[post[zIdx]]=zIdx;
                if(pre[zIdx]>=0) post[pre[zIdx]]=zIdx;
                sb.setCharAt(zIdx,'O');

            }
        }


        System.out.print(sb);
    }
}
