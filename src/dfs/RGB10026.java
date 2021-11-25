package dfs;

import java.util.Scanner;

public class RGB10026 {
    static int n;
    static char[][] map;
    static boolean[][] visited;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = Integer.parseInt(sc.nextLine());
        map = new char[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++) map[i] = sc.nextLine().toCharArray();

        sc.close();

        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    dfs(j, i, map[i][j]);
                    answer++;
                }
            }
        }
        System.out.print(answer + " ");

        visited = new boolean[n][n];
        answer = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 'G') map[i][j] = 'R';
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    dfs(j, i, map[i][j]);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int x, int y, char c){
        if(visited[y][x] || map[y][x] != c){
            return;
        }

        visited[y][x] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < n && nx >= 0 && ny < n && ny >= 0 && !visited[ny][nx] && map[ny][nx] == c){
                dfs(nx, ny, c);
            }
        }
    }
}
