package com.iotstudio.studiosignup.util.model;

public class ResponseModel {

    public static final String SUCCESS_MSG = "success";

    public static final String FAILED_MSG = "failed";

    private boolean successful;

    private String msg;

    private Object data;

    public ResponseModel() {
        this.successful = true;
        this.msg = SUCCESS_MSG;
    }

    public ResponseModel(Object data) {
        this.successful = true;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    public ResponseModel(boolean successful, String msg, Object data) {
        this.successful = successful;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
