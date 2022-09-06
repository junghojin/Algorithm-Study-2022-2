// BOJ - 달팽이3(1959번)
// 알고리즘엔 수학이 최고다..
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1959 {
    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);

        long min_value = (Math.min(n, m)-1) / 2;
        long x = min_value+1;
        long y = min_value+1;

        long cnt = min_value*4;
        n -= 2*min_value;
        m -= 2*min_value;

        if(n == 1){
            y += (m-1);
        } else if(m == 1){
            cnt++;
            x += (n-1);
        } else if(n == 2){
            cnt += 2;
            x++;
        } else {
            cnt += 3;
            x++;
        }


        System.out.println(cnt);
        System.out.println(x+" "+y);
    }
}
