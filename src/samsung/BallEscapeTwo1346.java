package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BallEscapeTwo1346 {
    static int row, col;
    static char[][] map;
    static boolean[][][][] checked;
    static int[] nx = {0, 0, -1, 1};
    static int[] ny = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        checked = new boolean[row][col][row][col];
        int rx = 0, ry = 0;
        int bx = 0, by = 0;

        System.out.println();
        for (int i = 0; i < row; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < col; j++) {
                System.out.print(line[j]);
            }
            System.out.println();
        }
    }
}
