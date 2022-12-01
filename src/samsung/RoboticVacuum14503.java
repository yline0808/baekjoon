package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RoboticVacuum14503 {

    static int n;
    static int m;
    static int cnt = 1;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int initY = Integer.parseInt(st.nextToken());
        int initX = Integer.parseInt(st.nextToken());
        int initD = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(initX, initY, initD);
        System.out.println(cnt);
    }

    public static void dfs(int x, int y, int dir) {
        map[y][x] = -1;

        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[ny][nx] == 0) {
                cnt++;
                dfs(nx, ny, dir);
                return;
            }
        }

        int back = (dir + 2) % 4;
        int bx = x + dx[back];
        int by = y + dy[back];

        if (bx >= 0 && bx < m && by >= 0 && by < n && map[by][bx] != 1) {
            dfs(bx, by, dir);
        }
    }
}
