package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*

long 범위를 int 로  사용해서 오래걸림.
- 모든 텔레포트를 고려해서 순서를 정하고 이동할때마다 도착지까지의 거리를 구하고 최소값을 갱신

 */
public class BJ_12908_텔레포트3 {

    static class Point {
        long x, y;
        Point(long x, long y){
            this.x = x;
            this.y = y;
        }

    }

    static Point start, end ;
    static Point[][] tels = new Point[6][2];
    static int[] nums;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));


        for(int i = 0; i < 3 ;i++) {
            st = new StringTokenizer(br.readLine());
            Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            tels[i][0] = new Point(p1.x, p1.y);
            tels[i][1] = new Point(p2.x, p2.y);
            tels[i+3][0] = new Point(p2.x, p2.y);
            tels[i+3][1] = new Point(p1.x, p1.y);
        }

        ans = Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
        nums = new int[6];
        move(0,0, start.x, start.y,new boolean[6]);
        System.out.println(ans);

    }
    static void move(int cnt, long time, long x, long y , boolean[] visited){

        ans = Math.min(ans, time + Math.abs(x- end.x)+Math.abs(y-end.y));

        if( cnt == 6){
            return;
        }
        for(int i = 0;i<6;i++){
            if(visited[i]) continue;
            visited[i] = true;
            nums[cnt] = i;
            long add = Math.abs(tels[i][0].x-x) + Math.abs(tels[i][0].y-y);
            move(cnt+1,time+add+10,tels[i][1].x,tels[i][1].y,visited);
            visited[i] = false;
        }
    }
}

