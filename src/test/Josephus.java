package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 요세푸스 문제 : https://www.acmicpc.net/problem/1158
 *
 * 0 1 2 3 4 5 6
 * 6 3 1 7 5 2 4
 *
 * 0 1 2 3 4 5 6
 * 3 6 2 7 5 1 4
 *
 *
 */

public class Josephus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.close();

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < n; i++) q.offer(i);
        while(q.size() > 1) {
            for (int i = 0; i < k - 1; i++) q.offer(q.poll());
            sb.append(q.poll() + ", ");
        }
        System.out.printf("<%s%d>", sb, q.poll());
    }
}
