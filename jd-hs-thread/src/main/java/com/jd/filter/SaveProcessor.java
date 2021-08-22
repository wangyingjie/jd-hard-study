package com.jd.filter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SaveProcessor extends Thread implements RequestProcessor {

    RequestProcessor nextProcessor;

    BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    volatile boolean finished = false;


    public SaveProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (!finished) {
            try {
                // 阻塞式的获取请求
                Request request = requests.take();
                System.out.println("Save:" + request);
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
        System.out.println("save begin shutDown!");
        finished = true;
        requests.clear();
        nextProcessor.shutDown();
    }
}
