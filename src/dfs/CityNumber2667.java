package dfs;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class CityNumber2667{

    // 위치를 담을 노드
    static class Node {
        int x;      // x좌표
        int y;      // y좌표

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // x, y좌표의 상 우 하 좌 뱡향으로 이동할 값
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());    // 입력받을 크기
        int[][] arr = new int[n][n];                // 입력받을 배열
        List<Integer> answer = new ArrayList<>();   // 정답 배열

        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();

            for (int j = 0; j < n; j++) {
                arr[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        sc.close();

        boolean[][] check = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 방문하지 않았으면서 현재 위치가 0이 아닌 경우
                if (!check[i][j] && arr[i][j] != 0)
                    //
                    answer.add(countCity(arr, check, n, i, j));
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for (int i : answer) System.out.println(i);
    }

    public static int countCity(int[][] arr, boolean[][] check, int n, int y, int x) {
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(x, y));
        check[y][x] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            x = now.x;
            y = now.y;
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !check[ny][nx] && arr[ny][nx] != 0) {
                    q.offer(new Node(nx, ny));
                    check[ny][nx] = true;
                }
            }
        }

        return cnt;
    }
}