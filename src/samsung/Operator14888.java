package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Operator14888 {
    static int n;
    static int[] arrA;
    static int[] oper;
    static int max = Integer.MIN_VALUE; //최대값
    static int min = Integer.MAX_VALUE; //최소값

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arrA = new int[n];
        oper = new int[4];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        //깊이우선 탐색 시작
        dfs(arrA[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    //지금까지 계산된 값: value와 arrA의 숫자의 순서인 idx 로 깊이우선 탐색
    public static void dfs(int value, int idx) {
        //모든 숫자를 계산했을 경우
        if (idx == n) {
            min = Math.min(min, value);
            max = Math.max(max, value);
            return;
        }
        //연산자 배열에 연산할 연산이 남아있을 경우 해당 연산 실행
        for (int i = 0; i < 4; i++) {
            if (oper[i] > 0) {
                oper[i]--;  //해당 연산 실행시 배열에서 제외
                switch(i){
                    case 0: //+
                        dfs(value + arrA[idx], idx + 1);
                        break;
                    case 1: //-
                        dfs(value - arrA[idx], idx + 1);
                        break;
                    case 2: //*
                        dfs(value * arrA[idx], idx + 1);
                        break;
                    case 3: ///
                        dfs(value / arrA[idx], idx + 1);
                        break;
                }
                oper[i]++;  //연산이 끝난 후 원복
            }
        }
    }
}
