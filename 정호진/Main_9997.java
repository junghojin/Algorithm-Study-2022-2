
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

public class Main {

    private static int N; // 단어의 개수
    private static String[] words; // 단어
    private static int result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        words = new String[N];

        int wordsLength = 0;
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine().trim();
            wordsLength += words[i].length();
        }

        // ===================== input end ================================

        if (wordsLength >= 26) {
            makeSentence(0, 0);
        }
        System.out.println(result);
    }

    private static void makeSentence(long alphaChecks, long wordsChecks) {
        // 모든 알파벳이 사용되었으면 문장 만들기 끝!!
        if (alphaChecks == ((1 << 26) - 1)) {
            result++;
            return;
        }

        if (wordsChecks == ((1 << N) - 1)) {
            return;
        }

        out:
        for (int i = 0; i < N; i++) {
            // 이미 사용한 단어라면 pass!
            if ((wordsChecks & (1 << i)) == (1 << i)) {
                continue;
            }

            // 단어에 사용한 알파벳 체크
            for (int j = 0; j < words[i].length(); j++) {
                int alphabet = words[i].charAt(j) - 'a';
                if ((alphaChecks & (1 << alphabet)) == (1 << alphabet)) { // 이미 사용한 알파벳을 가진 단어라면 채택 X
                    continue out;
                }
            }

            // 단어가 사용가능하다면 사용한 알파벳으로 추가
            for (int j = 0; j < words[i].length(); j++) {
                alphaChecks |= (1 << words[i].charAt(j));
            }

            // 단어 사용여부 체크한 뒤, 다음 단어 확인하러 gogo!
            makeSentence(alphaChecks, wordsChecks | (1 << i));
        }
    }
}
