import java.util.Scanner;

public class Main_5577 {

    private static int N, min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] balls = new int[N];
        for(int i = 0; i < N; i++) {
            balls[i] = sc.nextInt();
        }

        // 구슬 선택하기
        for(int i = 0; i < N; i++) {
            // 색 선택하기
            for(int color = 1; color <= 3; color++) {
                if(balls[i] == color) continue;
                int[] copied = balls.clone();
                copied[i] = color;
                pong(i - 1 , i + 1, color, copied, N);
            }
        }
        System.out.println(min);
    }

    // 구슬 터뜨리기
    private static void pong(int left, int right, int color, int[] balls, int N) {

       int cnt = 1;

        while(true) {
            while(left >= 0) {
                if(balls[left] != color) break;
                cnt++;
                left--;
            }

            while(right < N) {
                if(balls[right] != color) break;
                cnt++;
                right++;
            }

            if(cnt < 4) break;

            N -= cnt;

            if(left < 0 || right >= N || balls[left] != balls[right]) break;

            color = balls[left];

            cnt = 0;
        }
        min = Math.min(min, N);
    }
}
