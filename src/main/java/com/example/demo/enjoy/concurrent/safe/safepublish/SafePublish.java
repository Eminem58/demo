package com.example.demo.enjoy.concurrent.safe.safepublish;

/**
 * [演示基本类型的发布]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class SafePublish {
    private int i;

    public int getI() {
        return i;
    }

    public SafePublish(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        SafePublish safePublish = new SafePublish(100);
        int i = safePublish.getI();
        i = 1;
        System.out.println(safePublish.getI());
    }
}
