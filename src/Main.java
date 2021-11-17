import java.util.*;
import java.io.*;

/**
 * 14719 빗물
 * 1662 압축
 * 1158 : 요세푸스
 */

public class Main {
    public static void main(String[] args) {
        HashSet<Integer> hs1 = new HashSet<>();
        HashSet<Integer> hs2 = new HashSet<>();

        hs1.add(1);
        hs1.add(2);
        hs1.add(3);
        hs2.add(2);
        hs2.add(3);
        hs2.add(4);

        hs1.removeAll(hs2);

        int size = hs1.size();
        System.out.println("size = " + size);

    }
}
