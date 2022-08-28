import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9029_정육면체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][][] cube = new int[201][201][201];


        for(int w = 1; w<=200; w++){
            for(int l = 1; l<=200;l++){
                for(int h = 1; h<=200; h++){
                    if(cube[w][l][h] == 0){
                        if(w==1) cube[w][l][h] = l*h;
                        else if(l == 1) cube[w][l][h] = w * h;
                        else if(h == 1) cube[w][l][h] = w * l;
                        else if(w%h == 0 && l % h == 0) cube[w][l][h] = (w/h) * (l/h);
                        else if(w%l == 0 && h % l == 0) cube[w][l][h] = (w/l) * (h/l);
                        else if(l%w == 0 && h % w == 0) cube[w][l][h] = (l/w) * (h/w);
                        else{
                            cube[w][l][h] = Integer.MAX_VALUE;
                            for(int i = 1; i<=w/2;i++){
                                cube[w][l][h] = Math.min(cube[w][l][h], cube[i][l][h]+ cube[w-i][l][h]);
                            }
                            for(int i = 1; i<=l/2;i++) {
                                cube[w][l][h] = Math.min(cube[w][l][h], cube[w][i][h] + cube[w][l-i][h]);
                            }
                            for(int i = 1; i<=h/2;i++) {
                                cube[w][l][h] = Math.min(cube[w][l][h], cube[w][l][i] + cube[w][l][h-i]);
                            }
                        }
                        cube[w][h][l] = cube[w][l][h];
                        cube[h][l][w] = cube[w][l][h];
                        cube[h][w][l] = cube[w][l][h];
                        cube[h][l][w] = cube[w][l][h];
                        cube[w][l][h] = cube[w][l][h];
                        cube[w][h][l] = cube[w][l][h];
                    }
                }
            }
        }


        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            System.out.println(cube[W][L][H]);

        }

    }
}
