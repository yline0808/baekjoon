package dfs;

import java.util.Scanner;

class RGB10026 {
    static int n;               // 구역크기
    static char[][] map;        // 구역정보를 담을 배열
    static boolean[][] visited; // 방문여부 담을 배열
    // x, y위치 변화에 따른 증감 값
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);

        n = Integer.parseInt(sc.nextLine());    // 크기 입력
        map = new char[n][n];                   // 입력받은 크기로 초기화
        visited = new boolean[n][n];            // 입력받은 크기로 초기화
        // 구역정보 입력
        for(int i = 0; i < n; i++) map[i] = sc.nextLine().toCharArray();

        sc.close();

        // === 풀이 ===
        int[] answer = new int[2];     // 색맹이 아닌 사람이 구별한 구역의 개수를 담을 변수
        // 깊이 우선 탐색
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // 방문하지 않은 구역일 경우 깊이 우선 탐색
                if(!visited[i][j]){
                    dfs(j, i, map[i][j]);   // 깊이 우선 탐색
                    answer[0]++;            // 색맹 아닌 사람이 구별한 영역 카운트 증가
                }
            }
        }
        // 방문여부 초기화
        visited = new boolean[n][n];
        // 모든 G를 R로 변경
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 'G') map[i][j] = 'R';
            }
        }
        // 깊이 우선 탐색
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // 방문하지 않은 구역일 경우 깊이 우선 탐색
                if(!visited[i][j]){
                    dfs(j, i, map[i][j]);   // 깊이 우선 탐색
                    answer[1]++;            // 색맹인 사람이 구별한 영역 카운트 증가
                }
            }
        }

        // === 출력 ===
        for(int i : answer) System.out.print(i + " ");
    }

    // 깊이 우선 탐색
    public static void dfs(int x, int y, char c){
        // 방문했거나 다른색일경우
        if(visited[y][x] || map[y][x] != c) return;
        // 현재 위치 방문처리
        visited[y][x] = true;
        // 4방향 방문 시도
        for(int i = 0; i < 4; i++){
            // 다음 x, y좌표 계산
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 좌표가 범위내 존재 && 방문하지 않음 && 다음 값이 찾고자 하는 값과 일치
            if(nx < n && nx >= 0 && ny < n && ny >= 0 && !visited[ny][nx] && map[ny][nx] == c){
                dfs(nx, ny, c);     // 깊이 우선 탐색
            }
        }
    }
}
