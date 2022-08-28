import java.io.*;
import java.util.*;

public class BJ3430 {
    static class Node implements Comparable<Node>{
        int l,s,e;

        public Node(int l, int s, int e) {
            this.l = l;
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            if (this.s==o.s){
                return this.e-o.e;
            } else {
                return this.s-o.s;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Z = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Z; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] rain = new int[m];
            int[] lake = new int[n+1];

            st = new StringTokenizer(br.readLine());
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (int j = 0; j < m; j++) {
                rain[j] = Integer.parseInt(st.nextToken());
                if (rain[j]!=0){
                    pq.add(new Node(rain[j],lake[rain[j]],j));
                    lake[rain[j]]=j;
                }
            }

            List<Integer> list = new ArrayList<>();
            boolean tmp = true;

            for (int j = 0; j < m; j++) {
                if (rain[j]==0){
                    Node now = pq.peek();
                    if (now==null||j<now.s){
                        list.add(0);
                    }
                    else if (j>now.e){
                        tmp=false;
                        break;
                    } else {
                        pq.poll();
                        list.add(now.l);
                    }
                }
            }
            if (!pq.isEmpty()) tmp=false;

            if (tmp){
                sb.append("YES\n");
                for(int l:list){
                    sb.append(l+" ");
                }
                sb.append("\n");
            } else sb.append("NO\n");

        }
        System.out.println(sb);

    }
}
