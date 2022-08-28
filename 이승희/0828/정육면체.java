// BOJ - 정육면체(9029번)
// DP
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9029 {
    public static int[][][] box;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());
        box = new int[201][201][201];

        for(int w=1;w<=200;w++){
            for(int l=1;l<=200;l++){
                for(int h=1;h<=200;h++){
                    if(box[w][l][h] == 0){
                        if(w == 1){
                            box[w][l][h] = l*h;
                        } else if(l == 1){
                            box[w][l][h] = w*h;
                        } else if(h == 1){
                            box[w][l][h] = w*l;
                        } else if((l % w == 0) && (h % w == 0)){
                            box[w][l][h] = (l / w) * (h / w);
                        } else if((w % l == 0) && (h % l == 0)){
                            box[w][l][h] = (w / l) * (h / l);
                        } else if((w % h == 0) && (l % h ==0)){
                            box[w][l][h] = (w / h) * (l / h);
                        } else {
                            box[w][l][h] = Integer.MAX_VALUE;
                            for(int i=1;i<=w/2;i++){
                                box[w][l][h] = Math.min(box[w][l][h], box[i][l][h] + box[w-i][l][h]);
                            }

                            for(int i=1;i<=l/2;i++){
                                box[w][l][h] = Math.min(box[w][l][h], box[w][i][h] + box[w][l-i][h]);
                            }

                            for(int i=1;i<=h/2;i++){
                                box[w][l][h] = Math.min(box[w][l][h], box[w][l][i] + box[w][l][h-i]);
                            }
                        }

                        box[w][h][l] = box[w][l][h];
                        box[h][l][w] = box[w][l][h];
                        box[h][w][l] = box[w][l][h];
                        box[h][l][w] = box[w][l][h];
                        box[w][l][h] = box[w][l][h];
                        box[w][h][l] = box[w][l][h];
                    }
                }
            }
        }


        for(int test_case=1;test_case<=TC;test_case++){
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            System.out.println(box[w][l][h]);
        }
    }
}
