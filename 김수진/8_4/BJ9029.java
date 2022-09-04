import java.io.*;
import java.util.*;

public class BJ9029 {
    static int[][][] num = new int[201][201][201];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= W; j++) {
                for (int k = 0; k <= L; k++) {
                    for (int l = 0; l <= H; l++) {
                        num[j][k][l] = Integer.MAX_VALUE;
                    }
                }
            }
            sb.append(findMin(W,L,H)+"\n");

        }
        System.out.print(sb);
    }

    static int findMin(int w, int l, int h){
        if (num[w][l][h]!=Integer.MAX_VALUE) return num[w][l][h];
        if(w==1 || l==1 || h==1) return w*l*h;
        if(w==l&&w==h) return 1;
        for (int i = 1; i < w; i++) {
            num[w][l][h] = Math.min(num[w][l][h], findMin(w-i,l,h)+findMin(i,l,h));
        }
        for (int j = 1; j < l; j++) {
            num[w][l][h] = Math.min(num[w][l][h], findMin(w,l-j,h)+findMin(w,j,h));
        }
        for (int k = 1; k < h; k++) {
            num[w][l][h] = Math.min(num[w][l][h], findMin(w,l,h-k)+findMin(w,l,k));
        }
        num[l][w][h]=num[l][h][w]=num[w][h][l]=num[h][w][l]=num[h][l][w]=num[w][l][h];

        return num[w][l][h];
    }
}
