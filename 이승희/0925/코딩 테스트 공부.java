// 2022 카카오 인턴십 - 코딩 테스트
// DP
// 풀이 참조 (https://taehoung0102.tistory.com/211)
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int g_x = 0;
        int g_y = 0;
        
        for(int i=0;i<problems.length;i++){
            g_x = Math.max(problems[i][0], g_x);
            g_y = Math.max(problems[i][1], g_y);
        }
        
        if(g_x <= alp && g_y <= cop){
            return 0;
        }
        
        if(alp >= g_x){
            alp = g_x;
        }
        
        if(cop >= g_y){
            cop = g_y;
        }
        
        int[][] dp = new int[g_x+2][g_y+2];
        
        for(int i=alp;i<=g_x;i++){
            for(int j=cop;j<=g_y;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp;i<=g_x;i++){
            for(int j=cop;j<=g_y;j++){
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                
                for(int[] p:problems){
                    if(i >= p[0] && j >= p[1]){
                                            if(i+p[2] > g_x && j+p[3] > g_y){
                        dp[g_x][g_y] = Math.min(dp[g_x][g_y], dp[i][j]+p[4]);
                    } else if(i+p[2] > g_x){
                        dp[g_x][j+p[3]] = Math.min(dp[g_x][j+p[3]], dp[i][j]+p[4]);
                    } else if(j+p[3] > g_y){
                        dp[i+p[2]][g_y] = Math.min(dp[i+p[2]][g_y], dp[i][j]+p[4]);
                    } else if(i+p[2] <= g_x && j+p[3] <= g_y){
                        dp[i+p[2]][j+p[3]] = Math.min(dp[i+p[2]][j+p[3]], dp[i][j]+p[4]);
                    }
                    }

                }
            }
        }
        return dp[g_x][g_y];
    }
}
