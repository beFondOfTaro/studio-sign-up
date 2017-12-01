package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.util.fileutil.FileResponseModel;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileService {
    List<FileResponseModel> filesUpload(MultipartFile[] files, Integer userId,
                                        Integer preAssessmentReportId, String picClassify,
                                        String type, boolean isOverride);

    void fileDownload(HttpServletResponse httpServletResponse, String fileName);

    ResponseModel fileDelete(String fileName);
}
