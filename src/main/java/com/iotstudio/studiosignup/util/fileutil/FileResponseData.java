package com.iotstudio.studiosignup.util.fileutil;

public class FileResponseData {
    private String fileName;
    private boolean successful;
    private String msg;

    public FileResponseData() {
        this.successful = true;
    }

    public FileResponseData(String fileName) {
        this.successful = true;
        this.fileName = fileName;
    }

    public FileResponseData(String fileName, String msg) {
        this.successful = true;
        this.fileName = fileName;
        this.msg = msg;
    }

    public FileResponseData(String fileName, boolean successful) {
        this.fileName = fileName;
        this.successful = successful;
    }

    public FileResponseData(String fileName, boolean successful, String msg) {
        this.fileName = fileName;
        this.successful = successful;
        this.msg = msg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
