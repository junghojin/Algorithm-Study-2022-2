import java.util.Scanner;

public class Main_2613 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] beads = new int[N];
        int[] groups = new int[M];

        for(int i = 0; i < N; i++) {
            beads[i] = sc.nextInt();
        }

        // 이분 탐색
        int left = 1;
        int right = M+1;
        int minOfCases = Integer.MAX_VALUE;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(isAvailable(mid, N, M, beads)) {
                right = mid - 1;
                minOfCases = mid;
            } else {
                left = mid + 1;
            }
        }

        // 그룹 개수
        int i = 0;
        for(int j = 0; j < M; j++) {
            if(groups[j] == 0) {
                i = j - 1;
                groups[j]++;

                while(groups[i] == 1) {
                    i--;
                }

                groups[i]--;
            }
        }

        System.out.println(minOfCases);
        for(int j = 0; j < M; j++) {
            System.out.println(groups[j] + " ");
        }

    }

    private static boolean isAvailable(int mid, int N, int M, int[] beads) {
        int sum = 0;
        int idx = 0;

        int[] groups = new int[M];

        boolean flag = true;
        for(int i = 0; i < N; i++) {

            if(mid <= beads[i]) {
                flag = false;
                break;
            }

            if(idx == M) {
                flag = false;
                break;
            }

            if(sum + beads[i] <= mid) {
                sum += beads[i];
            } else {
                sum = beads[i];
                idx++;
            }

            groups[idx]++;
        }
        return flag;
    }
}
