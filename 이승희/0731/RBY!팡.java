// BOJ - RBY!팡(5577번)
// 구현 + 투포인터

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5577 {
    public static int n, min_value;
    public static int[] ball;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ball = new int[n];
        for(int i=0;i<n;i++){
            ball[i] = Integer.parseInt(br.readLine());
        }
        min_value = n;

        for(int i=0;i<n;i++){
            for(int j=1;j<=3;j++){
                if(ball[i] == j) continue;
                int origin = ball[i];
                ball[i] = j;
                play(ball);
                ball[i] = origin;
            }
        }

        System.out.println(min_value);


    }

    public static void play(int[] b){
        boolean[] visited = new boolean[n];

       while (true){
            boolean pang = false;
            int start = 0;
            outer :while (start < n) {
                if (visited[start]) {
                    start++;
                    continue;
                }

                int end = start + 1;
                int cnt = 1;
                int color = b[start];
                while (end < n) {
                    if (visited[end]) {
                        end += 1;
                        continue;
                    }
                    if (b[end] == color) {
                        end += 1;
                        cnt++;
                    } else {
                        break;
                    }
                }

                if(cnt >= 4){
                    pang = true;
                    for(int i=start;i<end;i++){
                        visited[i] = true;
                    }
                    break outer;
                } else {
                    start = end;
                    continue;
                }
            }

            if(!pang) break;

        }

        int cnt = 0;
        for(int i=0;i<n;i++){
            if(!visited[i]) cnt++;
        }
        min_value = Math.min(min_value, cnt);
    }
}
