package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartAndLink14889 {
    static int n;
    static int[][] arr;
    static boolean[] check;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        check = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(min);
    }

    public static void combination(int idx, int cnt) {
        if (cnt == n / 2) {
            min = Math.min(min, calculation());
            return;
        }

        for (int i = idx; i < n; i++) {
            check[i] = true;
            combination(idx + 1, cnt + 1);
            check[i] = false;
        }
    }

    public static int calculation() {
        int start = 0;
        int link = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(check[i] && check[j]) start += (arr[i][j] + arr[j][i]);
                if(!check[i] && !check[j]) link += (arr[i][j] + arr[j][i]);
            }
        }
        return Math.abs(start - link);
    }
}
