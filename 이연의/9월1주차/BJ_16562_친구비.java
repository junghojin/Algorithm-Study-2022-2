package Baekjoon;


/*

union 파인드를 하는데,
작은 비용을 가진 친구가 집합의 root 가 되도록
친구가 갖고 있어야할 정보 : root 친구, 비용
합쳐칠 때마다 비용이 더 큰쪽의 비용을 0으로 set하고 getParent 함수를 이용해 부모 친구를 root 친구로 변경한다.

자기 자신과 친구일 수도 있고, 같은 친구 관계가 여러 번 주어질 수도 있다. => 처리하지 않도록 하기

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16562_친구비 {


    static int N,M,K;
    static int[][] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        friends = new int[N+1][2];
        for(int i = 1 ; i <= N; i++){
            friends[i][0] = i;
            friends[i][1] = Integer.parseInt(st.nextToken());
        }

        int cost = 0;

        for(int i = 0;i< M;i++){

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a = getParent(a);
            b = getParent(b);

            if( a == b || friends[a][0] == friends[b][0]) continue;



            // b에 더 큰 비용이 오도록
            if(friends[a][1] >= friends[b][1]){
                int tmp = a;
                a = b;
                b = tmp;
            }
            //a에 합침
            for(int n = 1 ; n<=N; n++){
                if(friends[n][0] == b) {
                    friends[n][0] = a;
                    friends[n][1] = 0;
                }
            }
            friends[b][0] = friends[a][0];
            friends[b][1] = 0;

        }
        for(int [] f : friends){
            if(f[1] == 0 ) continue;
            cost += f[1];
        }
        if(cost <= K) System.out.println(cost);
        else System.out.println("Oh no");
    }
    static int getParent(int x){
        if(friends[x][0] == x) return x;
        else return friends[x][0] = getParent(friends[x][0]);
    }
}
