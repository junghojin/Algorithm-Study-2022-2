package BJ;

import java.io.*;
import java.util.*;

public class BJ12764 {

    static class Node implements Comparable<Node>{
        int a, b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.a-o.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Node[] useC = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            useC[i] = new Node(a,b);
        }

        Arrays.sort(useC);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        PriorityQueue<Integer> seat = new PriorityQueue<>();
        List<Integer> ans = new ArrayList<>();
        int cnt=0;

        for (int i = 0; i < N; i++) {
            while (!pq.isEmpty()&&pq.peek().a<=useC[i].a) {
                Node now = pq.poll();
                seat.add(now.b);
            }
            if (!seat.isEmpty()){
                int nSeat = seat.poll();
                ans.set(nSeat,ans.get(nSeat)+1);
                pq.add(new Node(useC[i].b,nSeat));
            } else {
                ans.add(1);
                pq.add(new Node(useC[i].b,cnt));
                cnt++;
            }
        }

        sb.append(cnt+"\n");
        for(int i:ans) sb.append(i+" ");
        System.out.print(sb);
    }
}
