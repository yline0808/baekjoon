package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Virus2606 {
    static List<Integer>[] arr; // 인접 리스트
    static boolean[] visited;   // 노드 방문여부
    static int answer;          // 결과

    public static void main(String[] args) {

        // === 입력 ===
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 노드 개수
        int len = sc.nextInt();     // 입력받을 개수

        arr = new ArrayList[n + 1];     // 인접리스트 n + 1만큼 생성
        visited = new boolean[n + 1];   // 방문 배열 n + 1만큼 생성
        // 인접 리스트 초기화
        for(int i = 0; i <= n; i++) arr[i] = new ArrayList<>();
        // 인접 리스트 양방향 추가
        for(int i = 0; i < len; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            arr[n1].add(n2);
            arr[n2].add(n1);
        }
        sc.close();

        // === 구현 ===
        // 여기서는 단순 카운트만 하면 되기때문에 인접리스트를 정렬 할 필요가 없음
        // 감염 시작인 1번 노드는 제외하기 위해 -1 로 초기화
        answer = -1;
        dfs(1);     // 깊이 우선 탐색

        // === 결과 ===
        System.out.println(answer);
    }
    // 깊이 우선 탐색
    public static void dfs(int idx){
        if(visited[idx]) return;    // 이미 방문했으면 return

        answer++;               // 현재 노드 카운트
        visited[idx] = true;    // 현재 노드 방문처리

        for(int i : arr[idx])
            if(!visited[i]) dfs(i);   // 현재 노드에서 갈 수 있는 노드 깊이우선으로 방문
    }
}
