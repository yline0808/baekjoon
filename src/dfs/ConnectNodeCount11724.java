package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ConnectNodeCount11724 {
    static List<Integer>[] arr;     // 인접리스트
    static boolean[] visited;       // 노드방문여부 배열

    public static void main(String[] args){
        // === 입력 ===
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 노드 개수
        int len = sc.nextInt();     // 입력받을 개수

        arr = new ArrayList[n + 1];     // 인접리스트 배열
        visited = new boolean[n + 1];   // 노드방문여부 배열
        for(int i = 0; i <= n; i++) arr[i] = new ArrayList<>(); // 인접리스트 초기화
        // 인접리스트 생성
        for(int i = 0; i < len; i++){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            arr[n1].add(n2);
            arr[n2].add(n1);
        }

        sc.close();

        // === 구현 ===
        // 여기서는 단순 카운트만 하면 되기때문에 인접리스트를 정렬 할 필요가 없음
        int answer = 0;

        for(int i = 1; i <= n; i++ ){
            // 방문하지 않은 노드 깊이 우선 탐색 후 answer 증가
            if(!visited[i]){
                dfs(i);
                answer++;
            }
        }

        // === 출력 ===
        System.out.println(answer);
    }
    // 깊이 우선 탐색
    public static void dfs(int idx){
        if(visited[idx]) return;    // 이미 방문한 노드일 경우 return
        // 현재 위치 방문처리
        visited[idx] = true;
        // 현재 노드에서 갈 수 있는 노드 반복
        for(int i : arr[idx]){
            if(!visited[i]) dfs(i); // 아직 방문하지 않은 노드가 있을 경우 깊이 우선 탐색
        }
    }
}
