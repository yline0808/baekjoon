package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laboratory14502 {
    static int n;
    static int m;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static int[][] vmap;            //바이러스가 퍼진상태의 지도
    //상하좌우 이동
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //벽 생성
        setWall(0);

        //결과
        System.out.println(max);
    }

    //벽생성 메서드
    public static void setWall(int wallCnt){
        //벽 3개가 모두 세워지면 안전구역 카운트 후 메서드 종료
        if (wallCnt == 3) {
            saveCnt();
            return;
        }
        //벽을 세울수 있는 모든 경우 세팅
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //벽을 설치가능하면 벽 설치후 벽 카운트 증가
                //모두 탐색했으면 벽을 제거해서 다음 경우의 수 계산
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    setWall(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    //안전구역 카운트
    public static void saveCnt(){
        //바이러스 위치정보를 담는 큐
        Queue<int[]> q = new LinkedList<>();

        //바이러스가 퍼젔을 당시의 지도
        vmap = new int[n][m];
        //지도 복사
        vmap = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);

        //바이러스가 있는 지점 큐에 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vmap[i][j] == 2) {
                    q.offer(new int[]{j, i});
                }
            }
        }

        //큐에있는 모든 바이러스 위치가 vmap에 저장될 때 가지 반복
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];

            //4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                //다음 위치가 바이러스 전염 가능 구역이면 바이러스 전염 후 큐에 다음 바이러스 위치 저장
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && vmap[ny][nx] == 0) {
                    vmap[ny][nx] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        //안전구역 카운트
        int cnt = (int)Arrays.stream(vmap)
                .flatMapToInt(Arrays::stream)
                .filter(n -> n == 0)
                .count();
        max = Math.max(max, cnt);
    }
}
