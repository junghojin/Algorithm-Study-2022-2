import java.util.*;

// 22.09.10.토 - 백준 1644 - 소수의 연속 합(골드3) - 소수, 투포인터

public class Main_1644 {

    private static int N;
    private static boolean isPrime[];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        isPrime = new boolean[N+1]; // 1부터 시작하기 때문
        List<Integer> primes = new LinkedList<>();
        primes = findPrime(); // 소수가 아닌 수 찾아 false로 만들기

        int cnt = 0; // 소수의 값들을 합쳐 N을 만들 수 있는 경우

        // 투포인터 이용하여 구간 합 구하기
        int left = 0;
        int right = 0;
        int sum = 0;

        while(left <= right) {
            if(sum >= N) {
                if(sum == N) cnt++;
                sum -= primes.get(left++);
            } else {
                // 마지막 right가 primes size를 벗어나는 경우 더이상 검색할 인덱스가 없기 때문에 break
                if(right >= primes.size()) break;
                sum += primes.get(right++);
            }
        }

        System.out.println(cnt);
    }


    // 소수가 아닌 수 찾기 - 에라토스테네스 체
    private static List<Integer> findPrime() {
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i * i <= N; i++) {
            if(isPrime[i]) {
                for(int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // LinkedList로 해줄 경우 시간초과
        // 왜? LinkedList는 데이터를 순차적으로 저장하여, 어떤 데이터를 찾기위해서는 처음부터 검색
        // ArrayList는 메모리 주소를 바탕으로 저장하기 때문에 주소식으로 값을 금방 찾을 수 있음
        // 배열의 주소 + index * 데이터의 크기
        // LinkedList는 데이터를 불연속적으로 저장하는 반면, ArrayList는 데이터를 연속적 묶음으로 저장하기 때문에
        // LinkedList에서 육안상 다음 위치에 존재한다해도 불연속적으로 이어진 객체를 찾는데 시간이 걸림
        // 데이터 삭제 및 수정이 빈번하게 일어나는 경우 LinkedList가 ArrayList보다 유리할 수 있음
        // ArrayList는 수정, 삭제 시 수정 삭제가 일어나는 index의 다음 위치 부터 끝까지 위치 이동을 해야하기 때문 (한 칸씩 shift)
        // 반면, LinkedList는 이어진 관계를 끊고, 맺으면 된다. 
        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i <= N; i++) {
            if(isPrime[i])
                primes.add(i);
        }
        return primes;
    }
}
