import java.util.*;
import java.io.*;

// 백준 3430 - 용이산다 - 2022.08.26

public class Main_3430 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < TC; tc++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int lake = Integer.parseInt(stk.nextToken());
            int rainyDay = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());
            List<Integer> nonRainyList = new ArrayList<>();
            boolean flag = true; // 재앙을 막을 수 있는지의 여부
            int lastRainyDay = 0; // 마지막으로 비가 온 날
            for(int i = 0; i < rainyDay; i++) {
                int info = Integer.parseInt(stk.nextToken());
                if(info == 0) { // 비가 오지 않으면 비오지 않는 날을 담는 리스트에 넣기
                    nonRainyList.add(0);
                } else { // 비가 온다면 비 오기 전 용이 물이 마실 수 있도록 하기
                    int idx = nonRainyList.size() + lastRainyDay - lake;
                    if(idx >= nonRainyList.size()) { // 비가 오지 않는 날 중 용이 물을 마실 수 있는 날이 없는 경우
                        flag = false;
                        break;
                    } else { // 비가 오지 않는 날 중 용이 물을 마실 수 있는 경우
                        if(nonRainyList.isEmpty() || nonRainyList.get(lastRainyDay) == info){
                            flag = false;
                            break;
                        } else {
                            lastRainyDay = idx;
                            nonRainyList.add(lastRainyDay, info);
                            nonRainyList.remove(lastRainyDay + 1);
                        }
                    }
                }
            }
            if(flag) {
                System.out.println("YES");
                for(int i = 0, size = nonRainyList.size(); i < size; i++) {
                    System.out.print(nonRainyList.get(i) + " ");
                }
                System.out.println();
            } else {
                System.out.println("NO");
            }
        }
    }
}
