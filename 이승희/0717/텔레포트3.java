// BOJ - 텔레포트(12908)
// 플로이드 워셜


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12908 {
    public static Node[] nodes;
    public static long[][] distance;
    public static int xs, ys, xe, ye;
    public static int INF = Integer.MAX_VALUE;

    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        nodes = new Node[8];
        distance = new long[8][8];

        xs = Integer.parseInt(st.nextToken());
        ys = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        xe = Integer.parseInt(st.nextToken());
        ye = Integer.parseInt(st.nextToken());

        nodes[0] = new Node(xs, ys);
        nodes[7] = new Node(xe, ye);
        for(int i=0;i<8;i++){
            Arrays.fill(distance[i], INF);
        }

        distance[0][7] = distance[7][0] = Math.abs(xs-xe) + Math.abs(ys-ye);
        for(int i=1;i<7;i+=2){
            st = new StringTokenizer(br.readLine(), " ");
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            nodes[i+1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            distance[i][i+1] = distance[i+1][i] = Math.min(Math.abs(nodes[i].x - nodes[i+1].x) + Math.abs(nodes[i].y - nodes[i+1].y), 10);
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                distance[i][j] = Math.min(distance[i][j], Math.abs(nodes[i].x-nodes[j].x) + Math.abs(nodes[i].y-nodes[j].y));
            }
        }


        for(int k=0;k<8;k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }


        System.out.println(distance[0][7]);

    }

}
