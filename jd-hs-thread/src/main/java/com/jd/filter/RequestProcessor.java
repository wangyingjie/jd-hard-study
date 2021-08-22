package com.jd.filter;

public interface RequestProcessor {

    void processRequest(Request request);

    void shutDown();
}
