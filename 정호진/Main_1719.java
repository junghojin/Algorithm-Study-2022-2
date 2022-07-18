// 22.07.19 - 백준 1719 - 택배 - 플로이드워셜

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1719 {

    public static void main(String[] args) throws Exception {
        int INF = 2000001;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        int[][] graph = new int[N+1][N+1];
        int[][] stopOvers = new int[N+1][N+1]; // 각 정점에서 다른 정점으로 향하기 위해 통과하는 경유지 (최소비용 보장)

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                graph[i][j] = INF;
                stopOvers[i][j] = j;
                stopOvers[j][i] = i;
            }
            graph[i][i] = 0;
        }

        for(int m = 0; m < M; m++) {
            stk = new StringTokenizer(br.readLine().trim(), " ");
            int n1 = Integer.parseInt(stk.nextToken());
            int n2 = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            graph[n1][n2] = cost;
            graph[n2][n1] = cost;
        }

        // =============== input end =================


        // 플로이드 와샬 : 모든 정점으로부터 타 모든 정점으로의 최소 비용을 꾸하는 알고리즘 (경유지 - 출발지 - 도착지)
        for(int k = 1; k <= N; k++) {
            for(int s = 1; s <= N; s++) {
                if(s == k) continue;
                for(int e = 1; e <= N; e++) {
                    if(e == k || e == s) continue;
                    if(graph[s][e] > graph[s][k] + graph[k][e]) {
                        graph[s][e] = graph[s][k] + graph[k][e];
                        stopOvers[s][e] = k;
                    }
                } // end e
            } // end s
        } // end k

        // 결과 출력
        StringBuilder result = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                    if(i==j) result.append("- ");
                    else {
                        int k = j;
                        while(stopOvers[i][k] != k) {
                            k = stopOvers[i][k];
                        }
                        result.append(stopOvers[i][k]).append(" ");
                    }
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }
}
