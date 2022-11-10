package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetro14500 {
    //높이, 너비
    static int n, m;
    //맵정보
    static int[][] map;
    //방문 확인용
    static boolean[][] check;
    //최소값
    static int max = Integer.MIN_VALUE;
    //상하좌우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        check = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //맵의 모든 경로 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                check[i][j] = true;
                solve(j, i, map[i][j], 1);
                check[i][j] = false;
            }
        }

        //결과
        System.out.println(max);
    }

    public static void solve(int x, int y, int sum, int cnt) {
        //무슨 도형이든 만들어지면 최대값 저장
        if (cnt == 4) {
            max = Math.max(max, sum);

            //동작 확인용
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(check[i][j]?'0':' ');
//                }
//                System.out.println();
//            }
//            System.out.println(sum);
            return;
        }
        //상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            //다음좌표
            int nx = x + dx[i];
            int ny = y + dy[i];

            //벽일경우 건너뜀
            if (ny < 0 || n <= ny || nx < 0 || m <= nx) {
                continue;
            }

            //방문하지 않은 곳이 있을경우 탐색
            if (!check[ny][nx]) {
                //ㅜㅗㅓㅏ의 도형의 경우 예외처리
                if (cnt == 2) {
                    check[ny][nx] = true;
                    solve(x, y, sum + map[ny][nx], cnt + 1);
                    check[ny][nx] = false;
                }

                check[ny][nx] = true;
                solve(nx, ny, sum + map[ny][nx], cnt + 1);
                check[ny][nx] = false;
            }
        }
    }
}
