package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
-문제-
스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안 된다.
이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
각각의 동작에서 공은 동시에 움직인다. 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.

-입력-
첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.
입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

-출력-
최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다. 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.

입력1
5 5
#####
#..B#
#.#.#
#RO.#
#####
-> 1

입력2
7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######
-> 5

입력3
7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######
-> 5

입력4
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########
-> -1

입력5
3 7
#######
#R.O.B#
#######
-> 1

입력6
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.##...#
#O..#....#
##########
-> 7

입력7
3 10
##########
#.O....RB#
##########
-> -1
 */

class BallEscapeTwo13460T {
    //row: 가로, col:세로
    static int row,col;
    static char[][] map;
    static boolean[][][][] checked;
    //최초 시도시 가장 큰 정수와 비교하기 위함
    static int min = Integer.MAX_VALUE;
    //우 좌 상 하
    static int[] dx = {1, -1, 0 ,0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        checked = new boolean[row][col][row][col];

        int rx =0, ry =0;   //빨간공 위치
        int bx =0, by =0;   //파란공 위치
        for(int i=0; i<row; i++) {
            String[] line = br.readLine().split("");
            for(int j=0; j<col; j++) {
                char val = line[j].charAt(0);
                map[i][j] = val;
                if(val == 'R') {
                    rx =i; ry=j;
                }else if(val == 'B') {
                    bx= i; by=j;
                }
            }
        }

        //계산
        bfs(rx,ry,bx,by,0);

        //출력
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void bfs(int rx, int ry, int bx, int by, int cnt) {
        //큐에 공들의 위치, 카운트 통으로 저장
        Queue<int[]> q = new LinkedList<>();
        //현재 정보 큐에 저장
        q.add(new int[] {rx,ry,bx,by, cnt});
        //현재 위치 체크
        checked[rx][ry][bx][by] =true;

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int pCnt = pos[4];

            if(pCnt>=10) {
                return;
            }
            //4방향 시도
            for(int i=0; i<4; i++){
                int nRx = pos[0];
                int nRy = pos[1];
                int nBx = pos[2];
                int nBy = pos[3];

                // 빨간 구슬 이동
                while(map[nRx+dx[i]][nRy+dy[i]] != '#') {
                    nRx += dx[i];
                    nRy += dy[i];
                    if(map[nRx][nRy] == 'O') break;
                }

                // 파란 구슬 이동
                while(map[nBx+dx[i]][nBy+dy[i]] != '#') {
                    nBx += dx[i];
                    nBy += dy[i];
                    if(map[nBx][nBy] == 'O') break;
                }

                //파란 구슬이 구멍에 들어갔을 때 해당 경우 패스
                if(map[nBx][nBy] == 'O') continue;

                //빨간공이 들어간 경우 가장 작은 카운트로 저장
                if(map[nRx][nRy] == 'O') {
                    min = Math.min(min, pCnt+1);
                    return;
                }

                // 빨간 파랑 서로 만났을 때
                if(nRx == nBx && nRy == nBy && map[nRx][nRy] != 'O') {
                    //이동한 거리를 비교하기 위함
                    int red_move = Math.abs(nRx-pos[0]) + Math.abs(nRy-pos[1]);
                    int blue_move = Math.abs(nBx-pos[2]) + Math.abs(nBy-pos[3]);

                    // 파란 공이 더 빨리 도착한 경우
                    if(red_move > blue_move) {
                        nRx -= dx[i];
                        nRy -= dy[i];
                    }else { // 빨간 공이 더 빨리 도착한 경우
                        nBx -= dx[i];
                        nBy -= dy[i];
                    }
                }

                //한번도 방문하지 않은 위치면 이동한 위치 체크 후 큐에 다음 위치와 정보 저장
                if(!checked[nRx][nRy][nBx][nBy]) {
                    checked[nRx][nRy][nBx][nBy] = true;
                    q.add(new int[] {nRx, nRy, nBx, nBy, pCnt+1});
                }
            }
        }

    }
}