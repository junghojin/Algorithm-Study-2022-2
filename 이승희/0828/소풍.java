// BOJ - 소풍(2026번)
// 백트래킹
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2026 {
    public static int k, n, f;
    public static boolean c = false;
    public static boolean[][] check;
    public static int[] friend_num;
    public static int[] friend;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        check = new boolean[n+1][n+1];
        friend_num = new int[n+1];
        for(int i=0;i<f;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            check[a][b] = true;
            check[b][a] = true;
            friend_num[a]++;
            friend_num[b]++;
        }
        friend = new int[k];
        boolean[] visited = new boolean[n+1];

        for(int i=1;i<=n;i++) {
            if(friend_num[i] < (k-1)) continue;
            dfs(1, i, visited);
            if(c) break;
        }
        if(!c) System.out.println(-1);
    }

    public static void dfs(int depth, int node, boolean[] visited){
        if(c) return;
        visited[node] = true;
        if(depth == k){
            c = true;
            for(int i=1;i<=n;i++){
                if(visited[i])
                    System.out.println(i);
            }
            return;
        }
        for(int i=1;i<=n;i++){
            if(visited[i] || !check[node][i]) continue;
            if(c) return;
            if(i != node && isfriend(visited, i)){
                dfs(depth+1, i, visited);
            }
        }
        visited[node] = false;
        return;

    }

    public static boolean isfriend(boolean[] visited, int node){
        for(int i=1;i<=n;i++){
            if(visited[i]){
                if(!check[i][node]) return false;
            }
        }
        return true;
    }

}
