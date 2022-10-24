package dfs;

import java.util.Scanner;

class Alphabet1987 {
    static int answer;      // 이동 거리
    static int h;           // 높이
    static int w;           // 너비
    static int[][] map;     // 지도 정보
    // A ~ Z 까지 0 ~ 25로 표현
    static boolean[] alpha = new boolean[26];
    // x, y좌표 이동시 이동할 크기
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);
        // 세로, 가로 크기 문자열로 입력 후 공백으로 나눔
        String[] tmp = sc.nextLine().split(" ");
        // 문자열로 되있는 세로와 가로를 정수로 변환
        h = Integer.parseInt(tmp[0]);
        w = Integer.parseInt(tmp[1]);
        // 입력받은 크기에 맞도록 초기화
        map = new int[h][w];
        // 지도정보 입력
        for(int i = 0; i < h; i++){
            String mapInfo = sc.nextLine();             // 행을 문자열로 입력받음

            for(int j = 0; j < w; j++){
                map[i][j] = mapInfo.charAt(j) - 'A';    // 문자로 하나씩 접근하며 알바펫을 0 ~ 25 숫자로 변환
            }
        }
        sc.close();

        // === 풀이 ===
        answer = 1;                 // 최소 크기가 1임으로 1로 정답 초기화
        dfs(0, 0, 0);   // 0, 0 위치부터 깊이 우선 탐색

        // === 출력 ===
        System.out.println(answer);
    }

    // 깊이 우선 탐색
    public static void dfs(int x, int y, int cnt){
        // 이미 방문했던 알파벳일 경우
        if(alpha[map[y][x]]) {
            // 지금까지 카운트한 cnt와 answer 비교해서 큰 값 저장
            answer = Math.max(answer, cnt);
            return;     // 깊이 우선 탐색 종료
        }
        // 현재 위치 방문처리
        alpha[map[y][x]] = true;
        // 4방향 모두 탐색 시도
        for(int i = 0; i < 4; i++){
            // 다음 위치 계산
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위내 다음 위치가 있을 경우
            if(nx >= 0 && nx < w && ny >= 0 && ny < h){
                // 카운트 증가하면서 다음 위치로 깊이 우선 탐색
                dfs(nx, ny, cnt + 1);
            }
        }
        // 다시 다른 방향 탐색을 위해 방문처리 해제 (백트래킹)
        alpha[map[y][x]] = false;
    }
}