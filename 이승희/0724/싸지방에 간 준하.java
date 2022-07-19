// BOJ - 싸지방에 간 준하(12764번)
// 우선순위큐
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12764 {
    public static class Node implements Comparable<Node>{
        int start;
        int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o){
            if(this.start == o.start){
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static class PQNode implements Comparable<PQNode>{
        int end;
        int idx;
        public PQNode(int end, int idx){
            this.end = end;
            this.idx = idx;
        }


        @Override
        public int compareTo(PQNode o) {
            return this.end - o.end;
        }
    }


    public static ArrayList<Node> list;
    public static int[] room = new int[100001];
    public static int n;
    public static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        PriorityQueue<PQNode> pq = new PriorityQueue<>();
        PriorityQueue<Integer> idx = new PriorityQueue<>();

        for (Node node:list){

            while (!pq.isEmpty() && pq.peek().end < node.start){
                idx.add(pq.poll().idx);
            }


            if(!idx.isEmpty()){
                Integer i = idx.poll();
                room[i] += 1;
                pq.add(new PQNode(node.end, i));
            } else {
                cnt++;
                room[cnt] += 1;
                pq.add(new PQNode(node.end, cnt));
            }



        }

        System.out.println(cnt);
        for(int i=1;i<=cnt;i++){
            System.out.print(room[i]+" ");
        }

    }
}
