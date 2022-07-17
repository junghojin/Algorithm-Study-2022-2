package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16118_달빛여우  {

    static class Tree implements Comparable<Tree>{
        int idx;
        long w;
        int type;

        public Tree(int idx, long w){
            this.idx = idx;
            this.w = w;
        }

        public Tree(int idx, long w, int type){
            this(idx,w);
            this.type = type;
        }

        @Override
        public int compareTo(Tree o){
            if(this.w  < o.w) return -1;
            else return 1;
        }
    }
    static int N, M;
    static ArrayList<Tree>[] trees;
    static long[] fox;
    static long[][] wolf;


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new ArrayList[N+1];
        fox = new long[N+1];
        wolf = new long[2][N+1];

        Arrays.fill(fox, Integer.MAX_VALUE);
        Arrays.fill(wolf[0], Integer.MAX_VALUE);
        Arrays.fill(wolf[1], Integer.MAX_VALUE);

        for(int i = 1; i <= N; i++) {
            trees[i] = new ArrayList<Tree>();
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            int s  = Integer.parseInt(st.nextToken());
            int e  = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            trees[s].add(new Tree(e,w*2));
            trees[e].add(new Tree(s,w*2));
        }


        foxDijkstra();
        wolfDijkstra();

        int ans = 0;
        for(int i = 2; i <= N; i++){
            if(fox[i] < Math.min(wolf[0][i], wolf[1][i])){
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void foxDijkstra(){
        PriorityQueue<Tree> pq = new PriorityQueue<>();
        pq.offer(new Tree(1,0));
        fox[1] = 0; // 시작 0으로 초기화

        while(!pq.isEmpty()){
            Tree cur = pq.poll();

            if(fox[cur.idx] < cur.w){
                continue;
            }

            for(Tree t : trees[cur.idx]){
                long cost = fox[cur.idx] + t.w;
                if(fox[t.idx] > cost ){
                    fox[t.idx] = cost;
                    pq.offer(new Tree(t.idx,cost));
                }
            }

        }
    }

    // 0 : 느리게, 1:빠르게
    static void wolfDijkstra(){
        PriorityQueue<Tree> pq = new PriorityQueue<>();
        pq.offer(new Tree(1,0,0));
        wolf[0][1] = 0;

        while(!pq.isEmpty()){
            Tree cur = pq.poll();

            if(wolf[cur.type][cur.idx] < cur.w) continue;

            for(Tree t : trees[cur.idx]){
                int type = cur.type == 1 ? 0 : 1;
                long cost = wolf[cur.type][cur.idx];
                cost += cur.type == 1 ? t.w*2 : t.w / 2;
                if(wolf[type][t.idx] > cost){
                    wolf[type][t.idx] = cost;
                    pq.offer(new Tree(t.idx,cost,type));
                }
            }

        }

    }

}
