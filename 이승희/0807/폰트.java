// BOJ - 폰트(9997번)
// 브루트포스 + 비트마스킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static int n, check;
    public static int cnt;
    public static ArrayList<Integer>[] str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        str = new ArrayList[n];
        check = 0;
        for(int i=0;i<n;i++){
            HashSet<Integer> hash = new HashSet<>();
            str[i] = new ArrayList<>();
            String s = br.readLine();
            for(int j=0;j<s.length();j++){
                int num = s.charAt(j) - 'a';
                if(!hash.contains(num)){
                    hash.add(num);
                    str[i].add(num);
                }

            }
        }

        dfs(0, new int[26]);
        System.out.println(cnt);
    }

    public static void dfs(int depth, int[] alpha){
        if(check == 26) {
            cnt += (1 << (n-depth));
            return;
        }
        if(depth == n){
            return;
        }


        for(int j=0;j<str[depth].size();j++){
            if(++alpha[str[depth].get(j)] == 1) check++;
        }


        dfs(depth+1,  alpha);
        for(int j=0;j<str[depth].size();j++) {
            if (--alpha[str[depth].get(j)] == 0) check--;

        }
        dfs(depth + 1, alpha);
        }


}
