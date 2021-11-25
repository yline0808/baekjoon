package dfs;

import java.util.PriorityQueue;
import java.util.Scanner;

class GetArea2583 {
    static int m;           // 세로 길이
    static int n;           // 가로 길이
    static int k;           // 네모의 개수
    static boolean[][] map; // 좌표정보를 담은 배열
    static int answer;      // 빈 영역을 카운트해줄 변수
    static PriorityQueue<Integer> pq = new PriorityQueue<>();   // 넓이 오름차순으로 출력해주기위해 사용한 우선순위큐
    // x, y 다음 좌표 변경시 증감 값
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();   // 세로 크기 입력
        n = sc.nextInt();   // 가로 크기 입력
        k = sc.nextInt();   // 사각형 개수 입력
        // 가로 세로 크기로 초기화
        map = new boolean[m][n];
        // 사각형 부분 방문처리
        for(int i = 0; i < k; i++){
            // 왼쪽 아래 좌표와 오른쪽 위 좌표 입력
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            // 해당 사각형 범위 방문처리
            for(int y = y1; y < y2; y++){
                for(int x = x1; x < x2; x++) map[y][x] = true;
            }
        }

        sc.close();

        // === 풀이 ===
        int cnt = 0;    // 영역 카운트변수 초기화
        answer = 0;     // 영역의 넓이 카운트변수 초기화
        // 깊이 우선 탐색
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 방문하지 않은 구역일 경우 깊이 우선 탐색
                if(!map[i][j]){
                    dfs(j, i);          // 깊이 우선 탐색
                    cnt++;              // 영역카운트 증가
                    pq.offer(answer);   // 영역 넓이 우선순위큐에 추가
                    answer = 0;         // 다음 영역 넓이를 카운트하기 위해 초기화
                }
            }
        }

        // === 출력 ===
        // 영역 개수 출력
        System.out.println(cnt);
        // 영역 넓이 오름차순으로 출력
        while(!pq.isEmpty()) System.out.print(pq.poll() + " ");
    }

    // 깊이 우선 탐색
    public static void dfs(int x, int y){
        if(map[y][x]) return;   // 이미 방문했거나 색칙된 부분일 경우 깊이 우선 탐색 중단

        map[y][x] = true;   // 현재 위치 방문처리
        answer++;           // 현재 위치가 포함된 넓이 증가
        // 4방향으로 깊이 우선 탐색 시도
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];     // 다음 x좌표
            int ny = y + dy[i];     // 다음 y좌표
            // map구역을 넘지 않으면서 다음 위치가 방문하지 않았거나 색칠된 영역이 아닌 경우
            if(nx >= 0 && nx < n && ny >= 0 && ny < m && !map[ny][nx]){
                dfs(nx, ny);        // 깊이 우선 탐색
            }
        }
    }
}
