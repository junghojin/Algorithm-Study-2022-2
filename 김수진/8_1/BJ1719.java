package BJ;

import java.io.*;
import java.util.*;

public class BJ1719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];
        int[][] ans = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i],5000000);
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            arr[a][b] = w;
            arr[b][a] = w;
            ans[a][b] = b;
            ans[b][a] = a;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int dis = arr[j][i] + arr[i][k];
                    if (dis<arr[j][k]){
                        ans[j][k] = ans[j][i];
                        arr[j][k] = dis;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i==j) sb.append("- ");
                else sb.append((ans[i][j]+1)+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
