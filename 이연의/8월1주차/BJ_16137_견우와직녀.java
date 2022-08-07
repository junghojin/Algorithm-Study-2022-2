package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16137_견우와직녀 {
    static int N,M;

    static int[][] map;
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i <N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0,0,false, new int[N][N]);
        System.out.println(ans);
    }
/*
견우 이동
1인곳
2이상 : 주기 % 이동횟수 == 0 인곳
==> + 1
0인곳 -> 걍 갈 수 있음
==> 지금 이동횟수 + 1

오작교 놓을 수 없는 곳 -> 4방 중 2곳 이상이 0인 곳


 */
    // 몇분 걸렸는지, 오작교 통해서 왔는지 아닌지, 오작교 하나 지었는지
    static boolean done;
    static int ans= Integer.MAX_VALUE;
    static void bfs(int x, int y, int time, boolean fromBridge, int[][] visit){

        if(x==N-1 && y==N-1){
            ans = Math.min(ans,time);
            done = false;
            return;
        }
        for(int d = 0 ; d < 4; d++){
            int mx = x + dx[d];
            int my = y + dy[d];

            if(mx < 0 || mx >= N || my < 0 || my >= N)continue;
            if(visit[mx][my] == 1) continue;

            // 1인 경우 -> 그냥 감
            if(map[mx][my] == 1){
                visit[mx][my] = 1;
               bfs(mx,my, time+1, false,visit);
                visit[mx][my] = 0;
            }else if (map[mx][my] == 0){ // 절벽인 경우
                if(!isAvailable(mx,my) || fromBridge || done )continue;
                //오작교 놓기
                done = true;
                visit[mx][my] = 1;
                bfs(mx,my,time+1, true,visit);
                visit[mx][my] = 0;
            }else{ // 오작교인 경우
                if( fromBridge || done )continue;
                if(((time+1) % map[mx][my]) == 0){ // 바로 건널 수 있는 경우
                    visit[mx][my] = 1;
                    bfs(mx,my,time+1, true,visit);
                    visit[mx][my] = 0;
                }else{
                    visit[mx][my] = 1;
                    bfs(mx,my,time+(map[mx][my] - (time+1) % map[mx][my]), true,visit);
                    visit[mx][my] = 0;
                }
            }

        }
    }

    static boolean isAvailable(int x, int y){ // 절벽에 교차하는지 확인
        int cnt = 0;
        for(int d = 0 ; d <4; d++){
            int mx = x + dx[d];
            int my = y + dy[d];

            if(mx < 0 || mx >= N || my < 0 || my >= N)continue;
            if(map[mx][my]==0) cnt++;
        }
        if(cnt >=2) return false;
        else return  true;
    }

}
