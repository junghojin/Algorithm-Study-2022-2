package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 사용시간을 pq 로 받아야함.
* */

public class BJ_12764_싸지방준하 {
    static class Time implements Comparable<Time>{
        int s,e;
        public Time(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Time o) {
            if(this.s == o.s)
                return this.e - o.e;
            return this.s - o.s;
        }
    }

    static int N;
    static int[] count;
    static PriorityQueue<Time> times = new PriorityQueue<>();
    static int[] endTimes;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        count = new int[N];
        endTimes = new int[N];

        StringTokenizer st = null;
        for(int i = 0 ; i < N;i++){
            st = new StringTokenizer(bf.readLine());
            times.add(new Time(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }


        int comCount = 0; // 현재 사용하고 있는 컴퓨터 수
        while(!times.isEmpty()){
            Time now = times.poll();
            boolean flag = false;
            for(int j = 0;j<comCount;j++){
                if(endTimes[j] < now.s){
                    count[j]++;
                    endTimes[j] = now.e;
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                count[comCount]++;
                endTimes[comCount++] = now.e;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(comCount).append("\n");
        for(int i = 0; i <comCount;i++)
            sb.append(count[i]+" ");
        System.out.println(sb.toString());
    }

}