package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LandCount4963 {
    static int[][] map;             // 섬 정보가 있는 2차원 배열
    static List<Integer> answer;    // 정답을 담을 배열
    // 12시 방향부터 시계방향으로 이동할 x, y의 값 차이
    static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);
        answer = new ArrayList<>();             // 정답배열 초기화

        while(true){
            // 가로세로 크기 입력
            int w = sc.nextInt();
            int h = sc.nextInt();
            // 만일 둘다 0일경우 문제 종료
            if(w == 0 && h == 0) break;
            // 현재 입력받은 크기로 map초기화
            map = new int[h][w];
            // map정보 입력
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    map[i][j] = sc.nextInt();
                }
            }

            sc.close();

            // === 구현 ===
            int cnt = 0;        // 몇개의 섬이있는지 카운트할 변수
            // map의 전체 탐색
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    // 만일 방문하지 않았거나, 섬이 있는 경우 깊이우선 탐색
                    if(map[i][j] != 0){
                        dfs(j, i, w, h);
                        cnt++;
                    }
                }
            }
            answer.add(cnt);        // 정답 추가
        }

        // === 출력 ===
        for(int i : answer) System.out.println(i);
    }
    // 깊이우선탐색
    public static void dfs(int x, int y, int w, int h){
        if(map[y][x] == 0) return;  // 현재 위치에 섬이 없거나 이미 방문한 경우 return
        // 현재위치 0으로 처리함으로 방문여부 처리
        map[y][x] = 0;
        // 8개의 방향 체크
        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 다음 방향이 map을 넘지 않으면서 섬이 존재하거나 아직 방문하지 않은경우
            if(nx >= 0 && nx < w && ny >= 0 && ny < h && map[ny][nx] != 0){
                dfs(nx, ny, w, h);      // 깊이우선탐색
            }
        }
    }
}
