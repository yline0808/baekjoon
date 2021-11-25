package dfs;

import java.util.Scanner;

public class SafeArea2468 {
    static int[][] map;
    static boolean[][] visited;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        sc.close();

        int answer = 0;
        for(int h = 0; h <= 100; h++){
            int cnt = 0;
            visited = new boolean[n][n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] > h && !visited[i][j]){
                        dfs(j, i, n, h);
                        cnt++;
                    }
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int n, int h){
        if(visited[y][x]) return;

        visited[y][x] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[ny][nx] > h && !visited[ny][nx])
                dfs(nx, ny, n, h);
        }
    }
}
