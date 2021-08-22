package com.jd.filter;

public class ChainServer {

    private RequestProcessor firstProcessor;

    private void setupRequestProcessor() {
        // 构建链路 print -> save -> final
        RequestProcessor finalProcessor = new FinalProcessor();
        RequestProcessor saveProcessor = new SaveProcessor(finalProcessor);
        firstProcessor = new FirstProcessor(saveProcessor);

        // 分表启动三个线程
        ((FinalProcessor) finalProcessor).start();
        ((SaveProcessor) saveProcessor).start();
        ((FirstProcessor) firstProcessor).start();


    }

    public void startup() {
        setupRequestProcessor();
    }

    public void shutdown() {
        firstProcessor.shutDown();
    }

    public static void main(String[] args) {
        ChainServer chainServer = new ChainServer();
        chainServer.startup();

        Request request = new Request("yj");
        chainServer.firstProcessor.processRequest(request);
        // chainServer.firstProcessor.processRequest(request);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chainServer.shutdown();
    }


}
