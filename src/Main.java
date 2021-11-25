import java.util.*;

class Main{
    static int answer;
    static int h;
    static int w;
    static char[][] map;
    static boolean[] alpha = new boolean[26];

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String[] tmp = sc.nextLine().split(" ");
        h = Integer.parseInt(tmp[0]);
        w = Integer.parseInt(tmp[1]);

        map = new char[h][w];
        for(int i = 0; i < h; i++) map[i] = sc.nextLine().toCharArray();

        sc.close();

        HashSet<Character> hs = new HashSet<>();
        hs.add(map[0][0]);
    }

}