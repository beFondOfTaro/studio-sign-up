package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.config.StudentInfoConfig;
import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.repository.StudentInfoRepository;
import com.iotstudio.studiosignup.repository.UserRepository;
import com.iotstudio.studiosignup.service.StudentInfoService;
import com.iotstudio.studiosignup.util.fileutil.FileUtil;
import com.iotstudio.studiosignup.util.fileutil.WrittenFileInfo;
import com.iotstudio.studiosignup.util.model.PageDataModel;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@Transactional
public class StudentInfoServiceImp implements StudentInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentInfoServiceImp.class);
    @Value("${file.uploadPath}")
    private String uploadPath;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private StudentInfoConfig studentInfoConfig;

    @Override
    public ResponseModel addOne(StudentInfo studentInfo) {
        return new ResponseModel(studentInfoRepository.save(studentInfo));
    }

    @Override
    public ResponseModel addOne(StudentInfo studentInfo, String userId, MultipartFile photoFile) {
        String msg;
        Object data;
        Integer intUserId = Integer.valueOf(userId);//用户id转为整型
        String photoAbsoluteSavePath = studentInfoConfig.getPhotoAbsoluteSavePath(userId);//照片的存储路径
        //验证学生信息是否已经存在，存在直接返回失败
        if (studentInfoRepository.findByUserId(intUserId) != null){
            msg = "已存在该学生信息，请不要重复添加";
            LOGGER.warn(msg);
            return new ResponseModel(msg);
        }
        //开始上传照片
        WrittenFileInfo fileInfo = FileUtil.fileUpload(photoFile,photoAbsoluteSavePath);
        //上传成功再将学生信息写入数据库，否则返回失败
        if (fileInfo.isSuccessful()){
            try {
                studentInfo.setUserId(intUserId);
                studentInfo.setPhoto(fileInfo.getFile().getName());
                data = studentInfoRepository.save(studentInfo);
                msg = "提交成功";
                LOGGER.error(msg);
                return new ResponseModel(msg,data);
            }catch (Exception e){
                LOGGER.error(e.getMessage());
                msg = "提交信息失败";
                return new ResponseModel(msg);
            }
        }else {
            msg = "照片上传失败";
            LOGGER.error(msg);
            return new ResponseModel(msg);
        }
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            studentInfoRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel deleteOneByUserId(String userId) {
        try {
            studentInfoRepository.deleteByUserId(Integer.valueOf(userId));
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(StudentInfo studentInfo) {
        return new ResponseModel(studentInfoRepository.save(studentInfo));
    }

    @Override
    public ResponseModel updateOneByUserId(StudentInfo studentInfo, String userId, MultipartFile photoFile) {
        String msg;//返回的消息
        StudentInfo originStudentInfo;
        File originPhoto = null;//原来的照片
        Integer intUserId = Integer.valueOf(userId);//用户id转为整型
        String photoAbsoluteSavePath = studentInfoConfig.getPhotoAbsoluteSavePath(userId);
        WrittenFileInfo fileInfo = FileUtil.fileUpload(photoFile,photoAbsoluteSavePath);
        if (fileInfo.isSuccessful()){
            //上传成功后删除原来的照片
            try {
                originStudentInfo = studentInfoRepository.findByUserId(intUserId);
                if (originStudentInfo == null){
                    msg = "不存在该学生信息！";
                    LOGGER.error(msg);
                    return new ResponseModel(msg);
                }
                originPhoto = new File(FileUtil.parsePath(photoAbsoluteSavePath) + originStudentInfo.getPhoto());
                //更新数据库信息;
                if (studentInfoRepository.updateByUserId(
                        intUserId,//用户id
                        studentInfo.getMajor(),//专业
                        originStudentInfo.getStudentNumber(),//学号,不允许用户修改
                        studentInfo.getQqNumber(),//QQ号
                        fileInfo.getFile().getName()//照片
                        ) == 1){
                    //删除原来照片
                    LOGGER.info("删除原来的照片");
                    FileUtil.deleteFile(originPhoto);
                    msg = "提交成功！";
                    LOGGER.info(msg);
                    return new ResponseModel(true,msg);
                }
            }catch (Exception e){
                LOGGER.error(e.getMessage());
                LOGGER.info("删除原来的照片");
                FileUtil.deleteFile(originPhoto);
            }
        }
        msg = "提交失败";
        LOGGER.error(msg);
        return new ResponseModel(msg);
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(studentInfoRepository.findOne(id));
    }

    @Override
    public ResponseModel findOneByUserId(String userId) {
        return new ResponseModel(studentInfoRepository.findByUserId(Integer.valueOf(userId)));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(studentInfoRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<StudentInfo> studentInfoPage = studentInfoRepository.findAll(pageable);
        PageDataModel studentInfoPageDataModel =
                new PageDataModel(
                        studentInfoPage.getTotalElements(),
                        studentInfoPage.getTotalPages(),
                        studentInfoPage.getContent()
                );
        return new ResponseModel(studentInfoPageDataModel);
    }
}
