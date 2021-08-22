package com.jd.filter;

import lombok.Data;

@Data
public class Request {

    String msg;

    public Request(String msg) {
        this.msg = msg;
    }

}
