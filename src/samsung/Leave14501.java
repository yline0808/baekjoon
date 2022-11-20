package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Leave14501 {
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] t = new int[n];
        int[] p = new int[n];
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        //dp로 점화식을 구해서 풀어야함
        /*
        해석 : 다음 완료일이 지정한 날을 넘지 않으면 해당 날짜의 dp와 지금 접수하려는 날의 dp를 비교 후 최대값을 다음 완료일에 넣어줌
        점화식 : if( i + ti ) dp[i + ti] = max( dp[i + ti], dp[i] + pi )
                dp[i + 1] = Math.max(dp[i + 1], dp[i])  //계속 다음값을 누적 더해주기 위해
         */
        for (int i = 0; i < n; i++) {
            //다음 예정일이 퇴사일을 넘지 않으면
            if(i+t[i] <= n){
                //해당 날짜의 dp와 지금 접수하려는 날의 dp를 비교 후 최대값을 다음 완료일에 넣어줌
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }
            //계속 다음값을 누적 더해주기 위해
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        //확인용 출력
//        for (int i = 0; i <= n; i++) {
//            System.out.println(dp[i]);
//        }
        System.out.println(dp[n]);
    }
}
