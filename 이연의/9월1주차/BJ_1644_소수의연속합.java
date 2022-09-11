package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1644_소수의연속합 {
    static boolean[] isPrime;
    static int[] primes;
    static int cnt=0, ans=0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        isPrime = new boolean[N+1];
        primes = new int[N];
        Arrays.fill(isPrime, true);
        makePrim(N);
        int start =0;
        int end = start;
        int sum  = primes[start];
        while(start != cnt && end != cnt){
            if( sum >= N){
                if( sum == N )
                    ans++;
                start++;
                end=start;
                sum = primes[start];
            }else{
                end++;
                sum += primes[end];
            }
        }
        if(sum == N) ans++;
        System.out.println(ans);
    }

    static void makePrim(int N){
        for(int i = 2; i <=N; i++){
            if(isPrime[i]){
                primes[cnt++] = i;
                for(int j = 2; j<= N/i; j++){
                    isPrime[i*j] = false;
                }
            }
        }
    }


}
