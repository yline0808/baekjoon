package test;

import java.util.Scanner;

/**
 * 빗물 : https://www.acmicpc.net/problem/14719
 */

class RainWater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt();
        int w = sc.nextInt();
        int[] map = new int[w];

        for(int i = 0; i < w; i++) map[i] = sc.nextInt();
        sc.close();

        int answer = 0;
        for(int i = 1; i < w - 1; i++){
            int now = map[i];   // 현재 벽 높이
            int left = now;     // 왼쪽 벽 최대높이
            int right = now;    // 오른쪽 벽 최대높이
            // 왼쪽 최대벽 탐색
            for(int j = i - 1; j >= 0; j--) left = Math.max(left, map[j]);
            // 오른쪽 최대벽 탐색
            for(int j = i + 1; j < w; j++) right = Math.max(right, map[j]);
            // 현재보다 높은 벽이 양쪽에 있을 경우
            if(Math.min(left, right) > now) answer += (Math.min(left, right) - map[i]);
        }
        System.out.println(answer);
    }
}
/*
   #
#  #
#  #
# ##

 */