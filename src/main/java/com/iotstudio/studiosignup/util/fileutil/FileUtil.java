package com.iotstudio.studiosignup.util.fileutil;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Component
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final int WIDTH = 300;

    /**
     * 多文件上传
     * @param files
     * @param path
     * @return
     */
    public static boolean filesUpload(MultipartFile[] files, String path){
        boolean flag = false;
        int count = 1;
        File userFile;
        for (MultipartFile file : files){
            if(!file.isEmpty()){
                try {
                    //区分存储路径是否有/
                    if (path.trim().lastIndexOf("/") == path.trim().length()-1){
                        userFile = new File(path+file.getOriginalFilename());
                    }
                    else {
                        userFile = new File(path + "/" +file.getOriginalFilename());
                    }
                    //下面创建父文件夹
                    File parentFile = userFile.getParentFile();
                    if (parentFile.exists()){
                        parentFile.mkdirs();
                    }
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(userFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    flag = true;
                    logger.info("第" + count + "个文件上传成功,位置:" + path + file.getOriginalFilename());
                } catch (IOException e) {
                    flag = false;
                    logger.error("第" + count + "个文件上传失败");
                    logger.error(e.getMessage());
                }
            }else{
                logger.error("第" + count + "个文件是空的");
            }
            count++;
        }
        if (count==1){
            logger.warn("没有文件上传");
        }
        return flag;
    }

    public static WrittenFileInfo fileUpload(MultipartFile multipartFile, String path){
        boolean flag = false;
        String suffix;
        File file=null;
        if (!multipartFile.isEmpty()){
            try {
                //文件名采用uuid，避免重名覆盖
                suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));//文件后缀名
                //区分存储路径是否有/
                if (path.trim().lastIndexOf("/") == path.trim().length()-1){
                    file = new File(path+ UUID.randomUUID()+suffix);
                }
                else {
                    file = new File(path + "/" + UUID.randomUUID()+suffix);
                }
                //下面创建父文件夹
                File parentFile = file.getParentFile();
                if (!parentFile.exists()){
                    parentFile.mkdirs();
                }
                //写入文件
                multipartFile.transferTo(file);
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new WrittenFileInfo(flag,file);
    }

    public static void fileDownload(HttpServletResponse httpServletResponse,File file){
        httpServletResponse.setHeader("content-type", "application/octet-stream");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = httpServletResponse.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createShortCutFile(File originFile) throws IOException{
        String originFileName = originFile.getName();
        String destFileName = originFileName.replace(".","_min.");
        String destFilePath = originFile.getParentFile().getAbsolutePath()+File.separator+destFileName;
        Thumbnails.of(originFile)
                .width(WIDTH)
                .toFile(destFilePath);
    }
}
