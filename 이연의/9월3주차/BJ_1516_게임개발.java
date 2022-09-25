package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*

위상정렬을 처음 배웠다.
인접 리스트와 dp 까지 활용한 재미있는 문제였다.
큐의 item 들 확인하면서 연결된 노드 업데이트 하고, 업데이트 할 때, dp를 이용하여 값을 갱신해주었다

건물을 짓고 연결된 건물의 기다린 시간(wait)을 업데이트 해줄때, 연결된 건물 중, 더 오래 기다려야하는 건물이
있을 수도 있기 때문에 저장된 시간과 업데이트할 시간 중 더 큰 값을 저장해야한다.
이 경우를 생각하지 못했다 ㅜ

 */
public class BJ_1516_게임개발 {

    static class Building{
        int time,num;
        ArrayList<Integer> link = new ArrayList<>();


    }

    static Building[] buildings;
    static int[] prevCnt, wait;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        buildings = new Building[N+1];
        prevCnt =  new int[N+1];
        wait = new int[N+1];
        for(int i = 1; i<= N; i++)
            buildings[i] = new Building();

        Queue<Building> q = new LinkedList<>();

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            buildings[i].num = i;
            buildings[i].time = Integer.parseInt(st.nextToken());
            int num = 0;
            while((num=(Integer.parseInt(st.nextToken())))!=-1){
                buildings[num].link.add(i);
                prevCnt[i]++;
            }
            if(prevCnt[i]==0){
                q.offer(buildings[i]);
            }
        }

        while(!q.isEmpty()){
            Building build = q.poll(); // 짓는 건물
            for(int i : build.link){
                wait[i] = Math.max(wait[i], build.time+wait[build.num]);
                if(--prevCnt[i] == 0){
                    q.offer(buildings[i]); // 지을 수 있는 건물 넣기
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N;i++) {
            sb.append(wait[i]+buildings[i].time).append('\n');
        }
        System.out.println(sb.toString());
    }
}
