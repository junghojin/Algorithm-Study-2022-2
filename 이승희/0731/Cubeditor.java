// BOJ - Cubeditor(1701번)
// KMP 알고리즘
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        int n = data.length();
        int max = 0;

        for(int i=0;i<n;i++){
            max = Math.max(max, solution(data.substring(i, n)));

        }
        System.out.println(max);

    }

    public static int solution(String str){
        int n = str.length();
        int j = 0;
        int m = 0;
        int[] pi = new int[n];
        for(int i=1;i<n;i++){
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j-1];
            if(str.charAt(i) == str.charAt(j)) m = Math.max(m, pi[i] = ++j);
        }
        return m;
    }
}
