package com.test.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {

    // 设置数量编号
    private int number = 1;
    //创建锁
    private Lock lock = new ReentrantLock();
    //创建3把钥匙
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(int loopNum) {
        lock.lock();
        try {
            //1 判断如果number !=1  让 c1.await()
            while (number != 1) {
                condition1.await();
            }
            //2 如果 number是1  则打印
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loopNum);
            }
            //3  唤醒c2
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public void print10(int loopNum) {
        lock.lock();
        try {
            //1 判断如果number !=2  让 c2.await()
            while (number != 2) {
                condition2.await();
            }
            //2 如果 number是2  则打印10次
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loopNum);
            }
            //3  唤醒c3
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print15(int loopNum) {
        lock.lock();
        try {
            //1 判断如果number !=3  让 c3.await()
            while (number != 3) {
                condition3.await();
            }
            //2 如果 number是2  则打印10次
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loopNum);
            }
            //3  唤醒c3
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}


/**
 * @author zzyy
 * @Description: 多线程之间按顺序调用，实现A->B->C				T1---T3
 * 三个线程启动，要求如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 * @date 2018年5月25日
 */
public class ThreadOrderAccess {


    public static void main(String[] args) {

        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print5(i);
            }
        } ,"A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print10(i);
            }
        } ,"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print15(i);
            }
        } ,"C").start();


    }


}
