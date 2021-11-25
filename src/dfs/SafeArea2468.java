package dfs;

import java.util.Scanner;

class SafeArea2468 {
    static int[][] map;         // 영역을 담을 배열
    static boolean[][] visited; // 방문여부를 담을 배열
    // 이동시 x, y 증감 값
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 지도 크기 입력
        // 입력받은 크기로 지도 초기화
        map = new int[n][n];
        // 지도 높이 입력
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        sc.close();

        // === 풀이 ===
        int answer = 0;     // 안전한 영역의 개수를 담을 변수
        // 비가 올 높이
        for(int h = 0; h <= 100; h++){
            int cnt = 0;    // 높이에 따른 안전영역 개수
            visited = new boolean[n][n];    // 방문여부 초기화
            // 모든 구역을 탐색하며 깊이 우선 탐색
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    // 침수되지 않았으면서 방문하지 않은 구역일 경우
                    if(map[i][j] > h && !visited[i][j]){
                        dfs(j, i, n, h);    // 깊이 우선 탐색
                        cnt++;              // 구역 카운트
                    }
                }
            }
            // 가장 많은 구역으로 answer 초기화
            answer = Math.max(answer, cnt);
        }

        // === 출력 ===
        System.out.println(answer);
    }

    // 깊이 우선 탐색
    public static void dfs(int x, int y, int n, int h){
        if(visited[y][x]) return;   // 방문한 위치일 경우 깊이 우선 탐색 종료
        // 현재 위치 방문처리
        visited[y][x] = true;
        // 4방향 탐색 시도
        for(int i = 0; i < 4; i++){
            // 다음 좌표 계산
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 구역안에 좌표가 존재 && 침수되지 않음 && 방문하지 않음
            if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[ny][nx] > h && !visited[ny][nx])
                dfs(nx, ny, n, h);  // 깊이 우선 탐색
        }
    }
}
