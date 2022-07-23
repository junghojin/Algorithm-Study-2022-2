package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_4991_로봇청소기  {

    static class Node implements Comparable<Node> {
        int x, y, cnt;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y,int cnt){
            this(x,y);
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }
    static int N, M , minDistance, ans;
    static char[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static ArrayList<Node> nodes;
    static int[][] distMatrix;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while(true) {
            st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            ans = Integer.MAX_VALUE;
            nodes = new ArrayList<>();

            if (N == 0 && M == 0) break;
            Node robot = null;
            for (int i = 0; i < N; i++) {
                map[i] = bf.readLine().toCharArray();
                for(int j = 0 ; j<M; j++){
                    if(map[i][j]=='o') robot = new Node(i,j);
                    else if(map[i][j]=='*') nodes.add(new Node(i,j));
                }
            }
            nodes.add(0,robot); // 로봇 제일 처음에
            int D = nodes.size(); // 로봇+먼지 갯수
            distMatrix = new int[D][D]; // 가중치 행렬

            if(makeMatrix(D)){ // 모든 더러운 칸을 갈 수 있는 경우
                // 가는 순서 순열
                permu(0,D,0,0,new boolean[D], new int[D]);

            }else{  // 모든 더러운 칸을 갈 수 없는 경우
                ans = -1;
            }
            System.out.println(ans);
        }

    }


    static boolean makeMatrix(int D){ // 전체 노드 가중치 행렬 생성

        for(int i = 0; i < D; i++){
            for(int j = i+1; j<D; j++){
                minDistance = Integer.MAX_VALUE;
                dustBFS(nodes.get(i), nodes.get(j),new boolean[N][M]);
                if(minDistance == Integer.MAX_VALUE){
                    return false;
                }
                // 가중치 행렬 생성
                distMatrix[i][j] = minDistance;
                distMatrix[j][i] = minDistance;
            }
        }
        return true;
    }

    static void dustBFS(Node start, Node end, boolean[][] visit){ // 각 노드 사이 최소 거리 구하기
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            Node now = queue.poll();
            if(visit[now.x][now.y]) continue;
            if( now.x == end.x && now.y == end.y){
                minDistance = Math.min(now.cnt, minDistance);
                continue;
            }
            visit[now.x][now.y] = true;

            for(int i = 0; i< 4;i++){
                int mx = now.x + dx[i];
                int my = now.y + dy[i];
                if(mx < 0 || mx >= N || my < 0 || my >=M
                        || map[mx][my]=='x') continue;

                queue.offer(new Node(mx,my,now.cnt+1));

            }
        }
    }
    static void permu(int cnt, int D, int prev, int sum, boolean[] visit, int[] num){
        if( cnt == D){
            ans = Math.min(ans, sum);
            return;
        }

        for(int i = 0; i < D; i++){
            if(visit[i]) continue;
            visit[i] = true;
            num[cnt] = i;
            permu(cnt+1, D, i,sum+ distMatrix[prev][i], visit, num );
            visit[i] = false;
        }
    }
}
