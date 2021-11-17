package test;

import java.util.HashMap;

class ElevenStreet3Ex {
    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3, 3,3,3};

        int answer = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int a : A) hm.put(a, hm.getOrDefault(a, 0) + 1);

        for(HashMap.Entry<Integer, Integer> e : hm.entrySet()){
            int key = e.getKey();
            int value = e.getValue();

            if(key > value){
                if(key - value > key / 2.0){
                    answer += value;
                }else{
                    answer += key - value;
                }
            }else if(key < value){
                answer += value - key;
            }else continue;
        }

        System.out.println("answer = " + answer);
    }
}
