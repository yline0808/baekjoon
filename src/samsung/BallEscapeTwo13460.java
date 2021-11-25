package samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BallEscapeTwo13460 {
    static int n;
    static int m;
    static char[][] map;

    static int hx, hy;
    static boolean[][][][] visited;
    static Ball blue, red;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] str = sc.nextLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        map = new char[n][m];
        visited = new boolean[n][m][n][m];

        for(int i = 0; i < n; i++) {
            String tmp = sc.nextLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);

                switch(map[i][j]){
                    case 'O':
                        hx = i;
                        hy = j;
                        break;
                    case 'B':
                        blue = new Ball(0, 0, j, i, 0);
                        break;
                    case 'R':
                        red = new Ball(j, i, 0, 0, 0);
                        break;
                }
//                if(map[i][j] == 'O') {
//                    hx = i;
//                    hy = j;
//                } else if(map[i][j] == 'B') {
//                    blue = new Ball(0, 0, i, j, 0);
//                } else if(map[i][j] == 'R') {
//                    red = new Ball(i, j, 0, 0, 0);
//                }
            }
        }

        System.out.println(bfs());

        sc.close();
    }

    public static int bfs() {
        Queue<Ball> queue = new LinkedList<>();
        queue.add(new Ball(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.rx][blue.ry] = true;

        while(!queue.isEmpty()) {
            Ball marble = queue.poll();

            int curRx = marble.rx;
            int curRy = marble.ry;
            int curBx = marble.bx;
            int curBy = marble.by;
            int curCnt = marble.cnt;

            if(curCnt > 10) { // 이동 횟수가 10 초과시 실패
                return -1;
            }

            for(int i = 0; i < 4; i++) {
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동 -> # 벽을 만날 때까지 이동
                while(map[newRx + dx[i]][newRy + dy[i]] != '#') {
                    newRx += dx[i];
                    newRy += dy[i];

                    // 이동 중 구멍을 만날 경우
                    if(newRx == hx && newRy == hy) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동 -> # 벽을 만날 때까지 이동
                while(map[newBx + dx[i]][newBy + dy[i]] != '#') {
                    newBx += dx[i];
                    newBy += dy[i];

                    // 이동 중 구멍을 만날 경우
                    if(newBx == hx && newBy == hy) {
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) { // 파란 구슬이 구멍에 빠지면 무조건 실패
                    continue; // 하지만 큐에 남은 다른 좌표도 봐야하니 다음으로
                }

                if(isRedHole && !isBlueHole) { // 빨간 구슬만 구멍에 빠지면 성공
                    return curCnt;
                }

                // 둘 다 구멍에 빠지지 않았는데 이동할 위치가 같은 경우 -> 위치 조정
                if(newRx == newBx && newRy == newBy) {
                    if(i == 0) { // 위쪽으로 기울이기
                        // 더 큰 x값을 가지는 구슬이 뒤로 감
                        if(curRx > curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    } else if(i == 1) { // 오른쪽으로 기울이기
                        // 더 작은 y값을 가지는 구슬이 뒤로 감
                        if(curRy < curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    } else if(i == 2) { // 아래쪽으로 기울이기
                        // 더 작은 x값을 가지는 구슬이 뒤로 감
                        if(curRx < curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    } else { // 왼쪽으로 기울이기
                        // 더 큰 y값을 가지는 구슬이 뒤로 감
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                }

                // 두 구슬이 이동할 위치가 처음 방문하는 곳인 경우만 이동 -> 큐에 추가
                if(!visited[newRx][newRy][newBx][newBy]) {
                    visited[newRx][newRy][newBx][newBy] = true;
                    queue.add(new Ball(newRx, newRy, newBx, newBy, curCnt+1));
                }
            }
        }

        return -1;
    }
}

class Ball{
    int rx;
    int ry;
    int bx;
    int by;
    int cnt;

    public Ball(int rx, int ry, int bx, int by, int cnt){
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }
}