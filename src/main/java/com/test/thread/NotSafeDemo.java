package com.test.thread;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 *  java.util.ConcurrentModificationException   并发修改错误
    CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
    复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
    再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
    而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
         public boolean add(E e) {
         final ReentrantLock lock = this.lock;
         lock.lock();
         try {
         Object[] elements = getArray();
         int len = elements.length;
         Object[] newElements = Arrays.copyOf(elements, len + 1);
         newElements[len] = e;
         setArray(newElements);
         return true;
         } finally {
         lock.unlock();
         }
        }
     Collection	Collections
     写时复制
     Collection 接口
     Collections 工具类				Collections.synchronizedList(list)
 */
public class NotSafeDemo {

    public static void main(String[] args){
        List<Object> list = new CopyOnWriteArrayList<>();
        Set<Object> objects = new CopyOnWriteArraySet<>();
        Map<String,String> map = new ConcurrentHashMap<String,String>();

        //List<Object> list = new ArrayList<>();

        for (int i = 0; i < 50 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            } ,String.valueOf(i)).start();

        }

    }


}
