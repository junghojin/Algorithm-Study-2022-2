package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9997_폰트 {
    static int N, ans, ansBit;
    static int[] alphaBit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());
        alphaBit = new int[N];
        ansBit = (1<<('z'-'a'+1))-1;

        for(int i = 0 ; i<N; i++){
            String input = br.readLine();
            int bit = 0;
            for(int j = 0 ; j < input.length(); j++){
                bit |= (1 << input.charAt(j)-'a');
            }
            alphaBit[i] = bit;
        }

        makeSentence(0, 0);
        System.out.println(ans);
    }

    static void makeSentence(int idx,   int visit){

        if(idx == N) {
            if(visit == ansBit){
                ans +=1;
            }
            return;
        }
        makeSentence(idx+1, visit | alphaBit[idx]);
        makeSentence(idx+1, visit);

    }
}
