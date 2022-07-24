package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2463_비용 {

    static final int MOD = 1000000000;
    static int N, M ,totalCost ,ans;
    static int[] parents, setCnt;
    static PriorityQueue<Edge> edges;

    static class Edge implements Comparable<Edge>{
        int x, y, w;
        public Edge(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w-this.w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "x=" + x +
                    ", y=" + y +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new PriorityQueue<>();

        for(int i = 0 ; i<M;i++){
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(x > y){
                int tmp = x;
                x = y;
                y = tmp;
            }
            edges.offer(new Edge(x,y,w));
            totalCost += w; // 전체 비용
        }// input

        initSet();

        //Cost(u,v) 계산
        int costSum=0;
        while(!edges.isEmpty()){
            Edge e = edges.poll();

            int p1 = findSet(e.x);
            int p2 = findSet(e.y);

            if( p1 != p2){
                ans += (setCnt[p1] * setCnt[p2] * (totalCost-costSum))%MOD;
                union(e.x,e.y);
            }

            costSum += e.w;

        }

        System.out.println(ans);
    }

    static void initSet(){
        parents = new int[N+1];
        setCnt = new int[N+1];
        for(int i = 1 ; i <= N; i++){
            parents[i] = i;
            setCnt[i] = 1;
        }
    }

    static int findSet(int x){
        if(x == parents[x]) return x;
        else return parents[x] = findSet(parents[x]);
    }
    static boolean union(int x, int y){
        int xR = findSet(x);
        int yR = findSet(y);

        if( xR == yR) return false;

        parents[yR] = xR;
        setCnt[xR]+=setCnt[yR];
        return true;

    }

}
