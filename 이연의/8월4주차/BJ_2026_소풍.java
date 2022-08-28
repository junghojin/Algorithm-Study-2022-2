package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 백트래킹 문제

 처음에는 간선 정보로 어떻게 서로 친구인 것을 확인할지 감이 안왔다.
 그래도 백트레킹으로 분류된 문제임을 알고있어서 완전탐샘 -> DFS 로 풀어야겠다는 힌트를 얻었다.

 N 명의 학생 중에서 K 명의 학생을 먼저 뽑고 서로 친구인지 확인한다.
 K명을 뽑을 때, 친구의 수가 K명인지 확인하고 K - 1 명 이상이라면 포함하고 그렇지 않으면 아예 포함하지 않는다.
 친구의 수는 입력을 받을 때 미리 friends 배열에 저장해 두었다.

 정답이 여러 경우라면 작은 번호부터 출력하면 되기 때문에 첫번째로 K명을 뽑았다면 바로 출력하고
 flag를 사용하여 이후에는 탐색하지 않도록 한다.


 */
public class BJ_2026_소풍 {

    static int K, N, F; // N명 중 K명 찾기, F개의 간선
    static boolean[][] adjMatrix;
    static int[] friends;
    static boolean flag = false;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        adjMatrix = new boolean[N+1][N+1];
        friends = new int[N+1];

        for(int i = 0 ; i < F; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjMatrix[v][w] = true;
            adjMatrix[w][v] = true;
            // 친구 수 저장
            friends[v] += 1;
            friends[w] += 1;
        }

        dfs(0,1,new boolean[N+1], new int[K]);
        if(flag) System.out.println(sb.toString());
        else System.out.println(-1);

    }

    // N 개중 k 개  선택 -> 선택한 k 개의 친구들이 서로 인접한지 확인
    static void dfs(int cnt, int idx, boolean[] visited, int[] nums){

        if( cnt == K ){
            // 서로 친구인지 확인
            for(int i = 0; i< K-1; i++){
                for(int j = i+1 ; j < K; j++){
                    if(!adjMatrix[nums[i]][nums[j]]) {
                        return;
                    }
                }
            }

            flag = true;

            for(int i : nums)
                sb.append(i).append('\n');

            return;
        }

        for(int i = idx ; i <= N; i++){
            // 친구 수가 K 가 되지 않으면 탐색 x
            if(visited[i] || friends[i] < K-1) continue;
            visited[i] = true;
            nums[cnt] = i;
            dfs(cnt + 1, i+1 , visited,nums);
            visited[i] = false;

            if(flag) return;

        }

    }

}