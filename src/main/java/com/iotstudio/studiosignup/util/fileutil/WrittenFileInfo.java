package com.iotstudio.studiosignup.util.fileutil;

import java.io.File;

/**
 * 文件保存后返回的信息模版
 */
public class WrittenFileInfo {

    private boolean isSuccessful;

    private File file;

    public WrittenFileInfo() {
    }

    public WrittenFileInfo(boolean isSuccessful, File file) {
        this.isSuccessful = isSuccessful;
        this.file = file;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
