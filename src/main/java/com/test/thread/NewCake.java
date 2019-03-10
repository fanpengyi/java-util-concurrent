package com.test.thread;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

/**
 * 1.
 * 线程  操作  资源类
 *
 * 高内聚 低耦合
 * 2.
 * 判断
 * 干活
 * 唤醒
 * 3 防止多线程的虚假唤醒
 *      多线程判断用while
 *
 */

public class NewCake {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try{
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public static void main(String[] args){
        NewCake cake = new NewCake();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cake.increment();
            }

        } ,"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cake.increment();
            }

        } ,"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cake.decrement();
            }

        } ,"C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cake.decrement();
            }

        } ,"D").start();





    }

}
