import java.util.*;
import java.io.*;

// 백준 9029 - 정육면체 - DP - 2022.08.28

public class Main_9029 {
    static int dp[][][];
    static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < TC; tc++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(stk.nextToken()); // 가로
            int L = Integer.parseInt(stk.nextToken()); // 세로
            int H = Integer.parseInt(stk.nextToken()); // 높이

            dp = new int[201][201][201]; // 육면체가 w, l, h 의 길이를 가질 때의 최소 조각 수
            // dp 초기화
            for(int w = 1; w <= 200; w++) {
                for(int l = 1; l <= 200; l++) {
                    for(int h = 1; h <= 200; h++) {
                        dp[w][l][h] = INF;
                    }
                }
            }
            cut(W, H, L);
            System.out.println(dp[W][L][H]);
        }
    }

    // 가로, 세로, 높이의 측면에서 자르는 함수
    private static int cut(int w, int l, int h) {
        if(w == l && l == h) { // 더이상 자를 수 없기 때문에 혹은 이미 정육면체이라면, 1 반환
            return 1; 
        }

        if(w == 1 || l == 1 || h == 1) { // 가로, 세로, 높이 중 한 변이라도 1일 경우 다른 측면에서 1로 맞춰 잘라줘야 한다.
            return w * l * h;
        }

        int widthPieces = INF; // 가로로 잘랐을 때 나올 수 있는 조각 수
        int lengthPieces = INF; // 세로로 잘랐을 때 나올 수 있는 조각 수
        int heightPieces = INF; // 높이로 잘랐을 때 나올 수 있는 조각 수
        int minPieces = dp[w][l][h];

        if(minPieces != INF) return minPieces;

        for(int i = 1; i <= w / 2; i++) { // 길이 1부터 가로 길이의 1/2까지 잘라 최소의 조각 수를 구한다.
            widthPieces = Math.min(widthPieces, cut(i, l, h) + cut(w-i, l, h));
        }
        for(int i = 1; i <= l / 2; i++) { // 길이 1부터 세로 길이의 1/2까지 잘라 최소의 조각 수를 구한다.
            lengthPieces = Math.min(lengthPieces, cut(w, i, h) + cut(w, l-i, h));
        }
        for(int i = 1; i <= h / 2; i++) { // 길이 1부터 높이 길이의 1/2까지 잘라 최소의 조각 수를 구한다.
            heightPieces = Math.min(heightPieces, cut(w, l, i) + cut(w, l, h-i));
        }

        // 가로, 세로, 높이 중 잘랐을 때 최소로 나온 조각 수
        minPieces = Math.min(Math.min(widthPieces, lengthPieces), heightPieces);

        // 이미 해당 길이를 잘라본 경우라면 자르는 것을 다시 반복해줄 필요가 없다.
        // 가로, 세로, 높이 중 잘랐을 때 최소로 나온 조각 수 dp에 저장해주기
        dp[w][l][h] = minPieces;
        dp[w][h][l] = minPieces;
        dp[l][w][h] = minPieces;
        dp[l][h][w] = minPieces;
        dp[h][w][l] = minPieces;
        dp[h][l][w] = minPieces;

        return minPieces;
    }
}
