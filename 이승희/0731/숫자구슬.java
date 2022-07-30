// BOJ - 숫자구슬
// Binary Search

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n, m;
    public static int[] arr;
    public static int lower, high;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            lower = Math.min(lower, arr[i]);
            high += arr[i];
        }

        // 이분 탐색
        int mid = 0;
        while (lower <= high){
            mid = (lower + high) / 2;
            int cnt = possible(mid);
            if(cnt > m) {
                lower = mid+1;
            } else {
                high = mid-1;
            }
        }
        System.out.println(lower+" "+high);
        sb.append(lower).append("\n");

        int cnt = 0;
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += arr[i];
            if(sum > lower){
                m--;
                sum = arr[i];
                sb.append(cnt).append(" ");
                cnt = 1;
            } else {
                cnt++;
            }

            if(m == n-i) break;
        }

        while (m-- > 0){
            sb.append(cnt).append(" ");
            cnt = 1;
        }
        System.out.println(sb.toString());
    }


    // 갯수 카운팅
    public static int possible(int mid){
        int sum = 0;
        int cnt = 1;
        for(int i=0;i<n;i++){
            sum += arr[i];
            if(sum > mid){
                cnt++;
                sum = arr[i];
            }
        }
        return cnt;
    }

}
