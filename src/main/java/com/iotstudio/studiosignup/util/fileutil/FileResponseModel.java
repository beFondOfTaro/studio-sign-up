package com.iotstudio.studiosignup.util.fileutil;

public class FileResponseModel {
    private String fileName;
    private String status;
    private String msg;

    public FileResponseModel() {
    }

    public FileResponseModel(String fileName, String status) {
        this.fileName = fileName;
        this.status = status;
    }

    public FileResponseModel(String fileName, String status, String msg) {
        this.fileName = fileName;
        this.status = status;
        this.msg = msg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
