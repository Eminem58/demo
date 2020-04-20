package com.example.demo.jvm;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/2/19
 */
public class HelloWord {
    public int add(){
        int a = 1;
        int b = 299;
        int c = a+b;
        return c;
    }
    public static void main(String[] args) {
         HelloWord helloWord = new HelloWord();
         int res = helloWord.add();
         System.out.println(res);
    }
}
