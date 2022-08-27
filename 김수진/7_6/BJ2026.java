import java.io.*;
import java.util.*;

public class BJ2026 {
    static int K, N, answer[];
    static boolean friends[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());

        friends = new boolean[N+1][N+1];

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a][b]=true;
            friends[b][a]=true;
        }
        answer = new int[K];

        BF(0,1);
        System.out.println(-1);
    }

    static void BF(int d, int idx){
        if (d==K) {
            for (int a:answer) System.out.println(a);
            System.exit(0);
        }
        t:for (int i = idx; i <= N; i++) {
            for (int j = 0; j < d; j++) {
                if (!friends[i][answer[j]]) continue t;
            }
            answer[d] = i;
            BF(d+1, i+1);
        }
    }
}