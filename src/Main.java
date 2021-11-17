import java.util.*;

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        TreeSet<Integer> tree = new TreeSet<>();
        Random random = new Random();
        int max = 10000000;
        ArrayList<Integer> randomNumber = new ArrayList<>();

        for(int i = 0; i < max; i++) randomNumber.add(random.nextInt());

        long start = System.currentTimeMillis();
        for(int i = 0; i < max; i++) arr.add(randomNumber.get(i));
        Collections.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println((end - start));


        start = System.currentTimeMillis();
        for(int i = 0; i < max; i++) tree.add(randomNumber.get(i));
        end = System.currentTimeMillis();
        System.out.println((end - start));
    }
}
