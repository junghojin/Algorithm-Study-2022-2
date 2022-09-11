package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2146_다리만들기 {
    static int N, cnt=0, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static class Point implements Comparable<Point>{
        int x, y, d;
        public Point(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Point o) {
            return this.d-o.d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        for(int i = 0; i < N;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine()," ");
            for(int j = 0; j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N;i++){
            for(int j = 0 ; j < N; j++){
                if(map[i][j]==1 && !visit[i][j]){
                    distinction(i,j,++cnt);
                }
            }
        }

        for(int c = 1 ; c <= cnt; c++){
            distance(c, new boolean[N][N]);
        }
        System.out.println(ans);

    }

    static void distinction(int x, int y, int cnt){ // 섬 구분

        Queue<Point> pq = new LinkedList<>();
        pq.add(new Point(x,y,0));
        visit[x][y] = true;

        while(!pq.isEmpty()){
            Point cur = pq.poll();
            int i = cur.x;
            int j = cur.y;

            map[i][j] = cnt;
            visit[i][j] = true;

            for(int d = 0; d <4; d++){
                int mi = i + dx[d];
                int mj = j + dy[d];

                if(mi < 0 || mi >=N || mj < 0 || mj >=N || visit[mi][mj]) continue;
                if(map[mi][mj]==1) pq.add(new Point(mi,mj,0));
            }
        }
    }
    static void distance(int cnt, boolean[][] visit){ // 거리 계산
        Queue<Point> pq = new LinkedList<>();

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j< N; j++){
                if(map[i][j] == cnt && !visit[i][j]){
                    visit[i][j] = true;
                    pq.add(new Point(i,j,0));
                }
            }
        }

        while(!pq.isEmpty()){
            Point cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int d = cur.d;

            for(int r = 0 ;r < 4 ; r++){
                int mx = x + dx[r];
                int my = y + dy[r];

                if(mx < 0 || mx >=N || my < 0 || my >=N  || visit[mx][my]) continue;
                if(map[mx][my] == 0){
                    if(d+1 <= ans){
                        visit[mx][my] = true;
                        pq.add(new Point(mx,my,d+1));
                    }

                }else if(map[mx][my] > cnt){
                    ans = Math.min(ans,d);
                    return;
                }

            }

        }

    }
}
