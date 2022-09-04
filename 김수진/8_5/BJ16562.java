import java.io.*;
import java.util.*;

public class BJ16562 {
    static int[] parent,price;

    static int findP(int i){
        return parent[i]=parent[i]==i?i:findP(parent[i]);
    }

    static void union(int a, int b){
        if (price[findP(a)]<price[findP(b)]) parent[findP(b)] = findP(a);
        else parent[findP(a)] = findP(b);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        price = new int[N];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) price[i] = Integer.parseInt(st.nextToken());

        List[] friends = new List[N];
        parent = new int[N];
        for (int i = 0; i <N; i++) {
            friends[i] = new ArrayList();
            parent[i] = i;
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken())-1;

            union(v,w);
        }

        boolean[] visited = new boolean[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int p = findP(i);
            if (visited[p]) continue;
            visited[p] = true;
            ans+=price[p];
        }

        if (ans>k) System.out.print("Oh no");
        else System.out.println(ans);
    }
}
