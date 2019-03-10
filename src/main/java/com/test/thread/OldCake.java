package com.test.thread;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OldCake {

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            wait(500);
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            wait(500);
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        notifyAll();
    }

    public static void main(String[] args) {
        OldCake oldCake = new OldCake();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    oldCake.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    oldCake.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    oldCake.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    oldCake.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "D").start();


    }


}
