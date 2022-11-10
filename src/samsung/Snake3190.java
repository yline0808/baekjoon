package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Snake3190 {
    static int n, d=0;
    static int[][] map;
    static Map<Integer,String> moveInfo;
    //우 상 좌 하
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());        //맵 크기
        int k = Integer.parseInt(br.readLine());    //사과 개수
        map = new int[n][n];
        moveInfo = new HashMap<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            // map에 저장하기 위해 위치 -1 저장
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1;
        }
        //뱀의 이동 정보 저장
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            moveInfo.put(time, direction);
        }

        solve();
    }
    static void solve() {
        Queue<String> q = new LinkedList<>();
        q.add("0,0");
        int time = 0;
        int px = 0;
        int py = 0;

        while (true) {
            int nx = px + dx[d];
            int ny = py + dy[d];
            time++;

            // 벽 부딪힘
            if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) {
                break;
            }

            // 몸통 부딪힘
            if (q.contains((ny+","+nx))) {
                break;
            }

            // 안 부딪히면 뱀 이동시작
            if (map[ny][nx] == 1) {
                map[ny][nx] = 0;
                q.add((ny+","+nx));
            } else {
                q.add((ny+","+nx));
                q.poll();
            }

            // 방향 전환
            if (moveInfo.containsKey(time)) {
                String data = moveInfo.get(time);
                if (data.equals("D")) {
                    d += 1;
                    if (d == 4) d = 0;
                } else {
                    d -= 1;
                    if (d == -1) d = 3;
                }
            }
            px = nx;
            py = ny;
        }
        System.out.println(time);
    }
}
