// 22.07.20 - 백준 골드3 - 싸지방에 간 준하

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12764 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Entrance> inputs = new PriorityQueue<>(Comparator.comparing(e -> e.start));
        PriorityQueue<Entrance> usingComputers = new PriorityQueue<>(Comparator.comparing(e -> e.end));
        PriorityQueue<Integer> remains = new PriorityQueue<>();
        int seats[] = new int[N];

        StringTokenizer stk = null;
        for (int n = 0; n < N; n++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());

            inputs.add(new Entrance(start, end, 0));
        }
        // ========== input end : 시작 시간이 빠른 순대로 정렬 ==========

        // 컴퓨터 사용하기
        int seatCnt = 0; // 사용중인 좌석
        while (!inputs.isEmpty()) {

            Entrance current = inputs.poll();
            int start = current.start;
            int end = current.end;

            // 아무도 컴퓨터를 사용하고 있지 않은 경우
            if (usingComputers.isEmpty()) {
                usingComputers.add(new Entrance(start, end, seatCnt));
                seats[seatCnt++]++;
                continue;
            }

            // 누군가 컴퓨터를 사용하고 있다면,
            while (!usingComputers.isEmpty()) {
                if (usingComputers.peek().end < start) {
                    // 컴퓨터 사용을 끝낸 사람이 있는지 검사
                    Entrance e = usingComputers.poll();
                    // 컴퓨터 사용을 끝낸 사람이 있다면 남은 좌석 배열에 넣기
                    remains.add(e.seatNo);
                } else {
                    break;
                }
            }

            if (remains.isEmpty()) {
                // 사용가능한 자리가 없을 경우 새로운 자리 배정
                usingComputers.add(new Entrance(start, end, seatCnt));
                seats[seatCnt++]++;
            } else {
                // 사용가능한 자리가 있을 경우 사용가능한 자리 중 작은 번호순대로 좌석 배정
                int seatNo = remains.poll();
                usingComputers.add(new Entrance(start, end, seatNo));
                seats[seatNo]++;
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(seatCnt).append("\n");
        for (int i = 0; i < seatCnt; i++) {
            answer.append(seats[i]).append(" ");
        }
        System.out.println(answer.toString());
    }

    private static class Entrance {
        int start;
        int end;
        int seatNo; // 차지하고 있는 좌석 번호

        public Entrance(int start, int end, int seatNo) {
            this.start = start;
            this.end = end;
            this.seatNo = seatNo;
        }

        @Override
        public String toString() {
            return "Entrance{" +
                    "start=" + start +
                    ", end=" + end +
                    ", seatNo=" + seatNo +
                    '}';
        }
    }
}
