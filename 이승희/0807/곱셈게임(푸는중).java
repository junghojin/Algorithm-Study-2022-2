import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12783_2 {
    public static int[] arr;
    public static int n, m, bit, num;
    public static int[] find;
    public static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());
        find = new int[1000001];
        for(int i=1;i<1000001;i++){
            int temp = 0;
            String str = String.valueOf(i);
            for(int j=0;j<str.length();j++){
                temp |= (1 << str.charAt(j)-'0');
            }
            find[i] = temp;
        }


        for(int test_case=1;test_case<=TC;test_case++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            bit = 0;

            arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(st.nextToken());
                bit |= (1 << arr[i]);
            }

            m = Integer.parseInt(br.readLine());

            for(int i=0;i<m;i++){
                num = Integer.parseInt(br.readLine());
                dp = new long[num+1];
                Arrays.fill(dp, -1);
                long min_cnt = solve(num);
                if(min_cnt == 9876543210L){
                    System.out.println(-1);
                } else {
                    System.out.println(min_cnt);
                }
            }
        }

    }

    public static long solve(int number){
        if(dp[number] != -1) return dp[number];

        if((bit & find[number]) == find[number]){
            dp[number] = 0;
        } else {
            dp[number] = 9876543210L;
        }
        for(int i=1;i<=(int)Math.sqrt(number);i++){
            if(number % i == 0){
                dp[number] = Math.min(dp[number], solve(i) + solve(number / i)+1);
            }
        }
        return dp[number];

    }
}
