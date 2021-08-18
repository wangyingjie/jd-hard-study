package com.jd.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wangyingjie5
 * @date 2021/8/18 11:21
 */
@Data
@AllArgsConstructor
public class Producer implements Runnable {

    private Queue<String> queueMsg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            lock.lock();
            try {
                while (queueMsg.size() == maxSize) {
                    System.out.println("生产则队列已满，先等待!");
                    // 阻塞线程并释放锁  await
                    condition.await();
                }
                Thread.sleep(1000);

                System.out.println("生产消息：" + i);
                queueMsg.add("生产的内容 " + i);

                // 唤醒阻塞状态下的线程 signal
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


}
