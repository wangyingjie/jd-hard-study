package com.jd.filter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FirstProcessor extends Thread implements RequestProcessor {

    RequestProcessor nextProcessor;

    BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    volatile boolean finished = false;


    public FirstProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }


    @Override
    public void run() {
        while (!finished) {
            try {
                // 阻塞式的获取请求
                Request request = requests.take();
                System.out.println("Print:" + request);
                nextProcessor.processRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }


    @Override
    public void shutDown() {
        System.out.println("print begin shutDown!");
        finished = true;
        requests.clear();
        nextProcessor.shutDown();
    }
}
