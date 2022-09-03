// BOJ - 친구비(16562번)
// union find

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16562 {
    public static int n, m, k;
    public static int[] money;
    public static ArrayList[] friend;
    public static int[] parent;
    public static int[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        money = new int[n+1];
        friend = new ArrayList[n+1];
        parent = new int[n+1];
        visited = new int[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<n+1;i++){
            friend[i] = new ArrayList<Integer>();
            money[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a].add(b);
            friend[b].add(a);

            if(find_parent(a) != find_parent(b)){
                union(a, b);
            }
        }
        int sum = 0;
        for(int i=1;i<=n;i++){
            int f = find_parent(i);
            int f_m = money[i];

            if(visited[f] == 0){
                visited[f] = f_m;
                sum += f_m;
            } else {
                if(visited[f] > f_m){
                    sum -= visited[f];
                    visited[f] = f_m;
                    sum += f_m;
                }
            }
        }


        if(sum > k){
            System.out.println("Oh no");
        } else {
            System.out.println(sum);
        }
    }

    public static int find_parent(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find_parent(parent[a]);
    }

    public static void union(int a, int b){
        a = find_parent(a);
        b = find_parent(b);
        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
