import java.util.*;
import java.io.*;

// 백준 7453 - 합이 0인 네 정수 - 이진탐색, 중복 고려 - 22.08.29

/** 
  참고:
  Set으로 풀었는데 시간 초과가 났다.
  Set의 경우, 출력에는 O(1)이 걸리지만 트리형태로 이루어지기 때문에 형성하는데 O(NlogN)이 걸린다고 한다.
  Set은 중복의 경우를 줄일 수 있어 고려한 자료구조이지만, A + B 혹은 C + D의 합을 Set에 저장할 경우 Set의 형성 과정이 상당히 부담스러워 시간초과가 날 수 있다.
*/

public class Main_7453 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // A의 개수는 int개 이고, 배열의 값도 int 범위 이내기 때문에 int type 을 사용해도 된다.
        // 단, 애매할 때는 long 타입을 쓰는 것이 안전하다.
        long[] A = new long[N];
        long[] B = new long[N];
        long[] C = new long[N];
        long[] D = new long[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());

            A[i] = Long.parseLong(stk.nextToken());
            B[i] = Long.parseLong(stk.nextToken());
            C[i] = Long.parseLong(stk.nextToken());
            D[i] = Long.parseLong(stk.nextToken());
        }

        // ================== input end ===============================

        long[] sum1 = new long[N*N];
        long[] sum2 = new long[N*N];

        // A + B , C + D 구하기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sum1[i * N + j] = A[i] + B[j]; // A와 B의 모든 합 구하기
                sum2[i * N + j] = C[i] + D[j]; // C와 D의 모든 합 구하기
            }
        }

        // 정렬
        Arrays.sort(sum1);
        Arrays.sort(sum2);

        // A + B + C + D = 0 찾기: 이진탐색
        int left = 0;
        int right = N * N - 1;
        // cnt가 long type인 이유는 cnt 가 최대 N^4 개 가능하고, 이는 4000^4 는 int의 크기를 벗어난다.
        long cnt = 0; // 0이 되는 쌍의 개수
        while(left < N * N && right >= 0) {
            long leftSum = sum1[left];
            long rightSum = sum2[right];
            long total = leftSum + rightSum;

            if(total < 0) {
                left++;
            } else if (total > 0) {
                right--;
            } else {

                // 중복되는 경우가 있는지를 찾아볼 것
                // long type이 아닐 때, 에러가 날 수 있다.
                // 왜? 총 N^4 개의 합의 경우가 생기는 데, N^4의 배열 값이 모두 0이라면
                // 중복 개수가 int의 범위를 초과하게되기 때문이다.
                long mutableSum1 = 0;
                long mutableSum2 = 0;

                while(left < N * N && leftSum == sum1[left]) {
                    mutableSum1++;
                    left++;
                }

                while(right >= 0 && rightSum == sum2[right]) {
                    mutableSum2++;
                    right--;
                }

                cnt += mutableSum1 * mutableSum2;
            }
        }

        System.out.println(cnt);
    }
}

