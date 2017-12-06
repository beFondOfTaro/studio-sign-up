package com.iotstudio.studiosignup.config;

import com.iotstudio.studiosignup.util.fileutil.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoConfig {
    @Value("${file.uploadPath}")
    private String uploadPath;

    /**
     * 获取照片的存储路径
     * @param userId 用户id
     * @return 照片的存储路径
     */
    public String getPhotoAbsoluteSavePath(String userId){
        return FileUtil.parsePath(uploadPath) + FileUtil.parsePath(userId) + "photo";
    }

    public String getUploadPath() {
        return uploadPath;
    }
}
