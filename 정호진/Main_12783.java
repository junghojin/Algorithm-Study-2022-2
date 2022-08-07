import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12783 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {

            StringTokenizer stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());

            ArrayList<Integer> nums = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                nums.add(Integer.parseInt(stk.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());

            int max = Integer.MIN_VALUE;
            int input[] = new int[M];

            for (int i = 0; i < M; i++) {
                input[i] = Integer.parseInt(br.readLine());
                max = Math.max(max, input[i]);
            }

            int[] d = new int[max + 1];
            Arrays.fill(d, -1);

            for (int j = 0; j <= max; j++) {
                String s = j + "";
                int length = s.length();
                if (length == 1) {
                    if (!nums.contains(s.charAt(0) - '0')) {
                        d[j] = -1;
                    }
                } else {
                    int tmp = d[j % ((int) Math.pow(10, length - 1))];
                    if (tmp == -1 || (tmp == 0 && !nums.contains(s.charAt(0) - '0'))) {
                        d[j] = -1;
                    }
                }
            }

            for (int i = 0; i <= max; i++) {
                if (d[i] == -1) continue;
                for (int j = 0, length = nums.size(); j < length; j++) {
                    int temp = i * nums.get(j);
                    if (temp <= max) {
                        if (d[temp] == -1) {
                            d[temp] = d[i] + 1;
                        } else {
                            d[temp] = Math.min(d[temp], d[i] + 1);
                        }
                    }
                }
            }

            // 결과
            for (int i = 0; i < M; i++) {
                System.out.println(d[input[i]]);
            }
        }
    }
}
