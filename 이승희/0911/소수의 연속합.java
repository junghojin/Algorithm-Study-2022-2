// BOJ - 소수의 연속합(1644번)
// 투포인터

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1644 {
    public static boolean[] prime;
    public static ArrayList<Integer> prime_list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        prime = new boolean[n+1];
        prime_list = new ArrayList<>();
        prime[0] = true;
        prime[1] = true;

        for(int i=1;i<=n;i++){
            prime[i] = isPrimeNumber(i);
            if(prime[i]) prime_list.add(i);
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = 0;

        while (true){
            if(sum >= n){
                sum -= prime_list.get(start++);
            } else if(end == prime_list.size()){
                break;
            } else {
                sum += prime_list.get(end++);
            }

            if(sum == n){
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    public static boolean isPrimeNumber(int number){
        // number가 1인 경우 소수가 아니어서 false
        if(number < 2){
            return false;
        }

        // 2부터 i의 제곱이 넘어온 number 변수보다 작을 경우 반복문을 수행
        for(int i=2;i*i<=number;i++){
            // 나머지가 0일 경우 소수가 아니어서 false
            if(number % i == 0){
                return false;
            }
        }

        // 그 외는 소수
        return true;
    }
}
