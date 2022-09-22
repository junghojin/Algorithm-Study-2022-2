// BOJ - 게임개발 (1516번)
// DFS + DP

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1516 {
    public static int n;
    public static int[] time;
    public static int[] ans;
    public static ArrayList<Integer>[] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        time = new int[n+1];
        ans = new int[n+1];
        connect = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            connect[i] = new ArrayList<Integer>();
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());
            while (true){
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) break;
                connect[i].add(num);
            }
        }

        for(int i=1;i<=n;i++){
            dfs(i);
            System.out.println(ans[i]);
        }
    }

    public static int dfs(int number){
        if(ans[number] != 0) return ans[number];
        ans[number] += time[number];
        int max = 0;
        for(int c:connect[number]){
            max = Math.max(max, dfs(c));
        }
        ans[number] += max;
        return ans[number];
    }
}
