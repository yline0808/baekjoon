package dfs;

import java.util.Scanner;

public class OrganicCabbage1012 {
    static int[][] map;     // 농사지 지도
    // 상 우 하 좌 방향으로 이동하기 위한 임시 변수
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);
        int answerLen = sc.nextInt();   // 문제 개수 입력

        // 문제 개수별로 실행
        for(int ansCnt = 0; ansCnt < answerLen; ansCnt++){
            int m = sc.nextInt();   // 가로 길이 입력
            int n = sc.nextInt();   // 세로 길이 입력
            int len = sc.nextInt(); // 입력 길이 입력

            map = new int[n][m];    // 지도 크기에 맞게 초기화
            // 각 위치별로 배추위치 설정
            for(int l = 0; l < len; l++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] = 1;
            }

            // === 출력 ===
            // solution 함수로 계산 후 바로 정답 출력
            System.out.println(solution(m, n));
        }
        sc.close();
    }
    // === 구현 ===
    public static int solution(int m, int n){
        int cnt = 0;    // 배추 지역을 카운트
        // 모든 지역을 탐색
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // 배추가 있을 경우 해당 위치에 인접한 배추 모두 0으로 변경후 배추지역 카운트
                if(map[i][j] == 1){
                    dfs(j, i, m, n);
                    cnt++;
                }
            }
        }
        // 배추지역 반환
        return cnt;
    }
    // 깊이 우선 탐색
    public static void dfs(int x, int y, int m, int n){
        // 현재 지역이 배추가 없거나 이미 탐색한 경우 return
        if(map[y][x] == 0) return;

        map[y][x] = 0;  // 0으로 초기화로 현재지역 방문처리
        // 4개의 인적한 배추 확인을 위함
        for(int i = 0; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            // map에 넘어가지 않으면서 다음 지역에 배추가 있을 경우 깊이 우선 탐색
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && map[ny][nx] == 1){
                dfs(nx, ny, m, n);
            }
        }
    }
}
