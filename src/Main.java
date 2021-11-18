import java.util.*;

class Main{
    static List<Integer>[] arr;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int len = sc.nextInt();

        arr = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++) arr[i] = new ArrayList<>();

        for(int i = 0; i < len; i++){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            arr[n1].add(n2);
            arr[n2].add(n1);
        }

        sc.close();

        answer = -1;
        dfs(1);

        System.out.println(answer);
    }

    public static void dfs(int idx){
        if(visited[idx]) return;

        visited[idx] = true;
        answer++;

        for(int i : arr[idx]){
            if(!visited[i]) dfs(i);
        }
    }
}