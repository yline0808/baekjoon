package dfs;

import java.util.Scanner;

public class Alphabet1987 {
    static int answer;
    static int[][] map;
    static boolean[] alpha = new boolean[26];

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String[] tmp = sc.nextLine().split(" ");
        int h = Integer.parseInt(tmp[0]);
        int w = Integer.parseInt(tmp[1]);

        map = new int[h][w];
        for(int i = 0; i < h; i++){
            String mapInfo = sc.nextLine();

            for(int j = 0; j < w; j++){
                map[i][j] = mapInfo.charAt(j) - 'A';
            }
        }
        sc.close();

        answer = 1;
        dfs(0, 0, 0, w, h);

        System.out.println("answer = " + answer);
    }

    public static void dfs(int x, int y, int cnt, int w, int h){
        if(alpha[map[y][x]]) {
            answer = Math.max(answer, cnt);
            return;
        }

        alpha[map[y][x]] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < w && ny >= 0 && ny < h && !alpha[map[ny][nx]]){
                dfs(nx, ny, cnt + 1, w, h);
            }
        }
        alpha[map[y][x]] = false;
    }
}
