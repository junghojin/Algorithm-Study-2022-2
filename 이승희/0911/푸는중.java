class Solution {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] b;
    public int solution(int[][] board) {
        int answer = 0;
        b = board;
        
        
        
        return answer;
    }
    
    
    
    public static boolean check_box(int x, int y){
        public static int[] bx = {0, 0, 1, 1};
        public static int[] by = {0, 1, 0, 1};
        for(int d=0;d<4;d++){
            int nbx = x + bx[d];
            int nby = y + by[d];
            if(nbx < 1 || nbx > b.length || nby < 1 || nby > b.length) continue;
            if(b[nbx][nby]==1) return false;
        }
        return true;
    }
    
}
