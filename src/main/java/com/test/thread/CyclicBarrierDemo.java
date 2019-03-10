package com.test.thread;


import java.util.concurrent.CyclicBarrier;

/**
 *
 * @Description:
 * CyclicBarrier
 * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有
 * 被屏障拦截的线程才会继续干活。
 * 线程进入屏障通过CyclicBarrier的await()方法。
 *
 * 集齐7颗龙珠就可以召唤神龙
 * @author zzyy
 * @date 2018年5月25日
 */
public class CyclicBarrierDemo {

    private static final int NUMBER = 7;

    public static void main(String[] args){
        //构造方法 CyclicBarrier(int parties,Runnable action)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Thread(() -> {
            System.out.println("集齐龙珠 召唤神龙");
        }));

        for (int i = 0; i <= NUMBER ; i++) {
        final int tempInt = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 收集了"+tempInt+"号龙珠");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } ,String.valueOf(i)).start();

        }



    }
}
