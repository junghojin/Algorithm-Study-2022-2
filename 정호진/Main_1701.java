import java.util.Scanner;

public class Main_1701 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine().trim();

        int totalMaxLength = Integer.MIN_VALUE; // 두 번 이상 나오는 문자열 중 가장 긴 길이

        for(int i = 0, length = input.length(); i < length; i++) {

            String subString = input.substring(i, length);

            int prefix = 0;
            int subStringLength = subString.length();
            int max = Integer.MIN_VALUE; // subString에서 가장 긴 문자열의 길이

            int[] chars = new int[subStringLength];

            for(int j = 1; j < subStringLength; j++) {

                while(prefix > 0 && subString.charAt(prefix) != subString.charAt(j)) {
                    prefix = chars[prefix - 1];
                }

                if(subString.charAt(prefix) == subString.charAt(j)) {
                    max = Math.max(max, ++prefix);
                    chars[j] = prefix;
                }
            }
            totalMaxLength = Math.max(totalMaxLength, max);

        }

        System.out.println(totalMaxLength == Integer.MIN_VALUE ? 0 : totalMaxLength);

    }
}

