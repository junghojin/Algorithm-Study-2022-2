package BJ;

import java.io.*;
import java.util.*;

public class BJ12908 {
    static long tp[][],xe,ye,ans=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long xs = Long.parseLong(st.nextToken());
        long ys = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        xe = Long.parseLong(st.nextToken());
        ye = Long.parseLong(st.nextToken());

        tp = new long[3][4];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                tp[i][j] = Long.parseLong(st.nextToken());;
            }
        }

        DFS(0,xs,ys,0);
        System.out.print(ans);
    }

    static void DFS(int depth, long x, long y, long value){
        if (value>ans) return;
        if (depth==4) ans = Math.min(ans, value+Math.abs(xe-x)+Math.abs(ye-y));
        else{
            for (int i = 0; i < 3; i++) {
                long nV = value+Math.abs(tp[i][0]-x)+Math.abs(tp[i][1]-y)+10;
                DFS(depth+1, tp[i][2], tp[i][3], nV);
            }
            for (int i = 0; i < 3; i++) {
                long nV = value+Math.abs(tp[i][2]-x)+Math.abs(tp[i][3]-y)+10;
                DFS(depth+1, tp[i][0], tp[i][1], nV);
            }
            DFS(depth+1, x,y,value);
        }
    }
}