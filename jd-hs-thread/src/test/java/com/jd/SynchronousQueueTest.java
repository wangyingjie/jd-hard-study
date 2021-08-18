package com.jd;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author wangyingjie5
 * @date 2021/8/17 19:56
 */
public class SynchronousQueueTest {

    @Test
    public void testQueue() throws InterruptedException{
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            // try {
            //     //System.out.println("take====>" );
            //     String take = queue.take();
            //     System.out.println("take====>" + take);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }).start();

        // queue.put("aaaa");
        // queue.put("bbbb");
        queue.add("bbbb");

        System.out.println(queue.size());
    }
}

