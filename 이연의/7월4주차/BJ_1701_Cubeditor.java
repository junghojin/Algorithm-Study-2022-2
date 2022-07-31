
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1701_Cubeditor {

    static int[] makeTable(String pattern){
        int patternSize = pattern.length();
        int[] table = new int[patternSize];

        int j = 0;
        for(int i = 1; i<patternSize; i++){
            while( j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                j = table[j-1];
            }
            if(pattern.charAt(i) == pattern.charAt(j)){
                table[i] = ++j;
            }
        }
        return table;
    }

    static void KMP(String text, String pattern){
        int textSize = text.length();
        int patternSize = text.length();
        int[] table = makeTable(pattern);
        int j = 0;

        for(int i = 0 ; i < textSize; i++){
            while(j>0 && pattern.charAt(i) != pattern.charAt(j)){
                j = table[j-1];
            }
            if(text.charAt(i) == pattern.charAt(j)){
                if(j == patternSize - 1 ){
                    j = table[j];
                }else{
                    j++;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String text = bf.readLine();
        for(int i = 0 ;i<text.length();i++){
            String tmp = text.substring(0,i+1);
            System.out.println(tmp);
            KMP(text,tmp);
        }
    }
}
