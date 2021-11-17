package test;

import java.util.Scanner;
import java.util.Stack;

/**
 * 압축 : https://www.acmicpc.net/problem/1662
 */

class Zip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();

        Stack<Integer> len = new Stack<>();
        Stack<Integer> mul = new Stack<>();

        int answer = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                len.push(--answer);
                mul.push(Character.getNumericValue(str.charAt(i - 1)));
                answer = 0;
            } else if (str.charAt(i) == ')') {
                int val = answer * mul.pop();
                answer = val + len.pop();
            } else answer++;
        }

        System.out.println("answer = " + answer);
    }
}
/*
int answer = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '('){
                answer--;
                len.add(answer);
                mul.add(Character.getNumericValue(str.charAt(i - 1)));
                answer = 0;
            }
            else if(c == ')'){
                int val = mul.pop() * answer;
                answer = len.pop() + val;
            }
            else answer++;
        }
        System.out.println("answer = " + answer);


 */