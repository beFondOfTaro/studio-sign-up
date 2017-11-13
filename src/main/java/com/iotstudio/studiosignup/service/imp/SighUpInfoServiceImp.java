package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.repository.SighUpInfoRepository;
import com.iotstudio.studiosignup.service.SighUpInfoService;
import com.iotstudio.studiosignup.util.model.PageDataModel;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SighUpInfoServiceImp implements SighUpInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SighUpInfoServiceImp.class);

    @Autowired
    private SighUpInfoRepository sighUpInfoRepository;

    @Override
    public ResponseModel addOne(SighUpInfo sighUpInfo) {
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            sighUpInfoRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(SighUpInfo sighUpInfo) {
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(sighUpInfoRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(sighUpInfoRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<SighUpInfo> sighUpInfoPage = sighUpInfoRepository.findAll(pageable);
        PageDataModel sighUpInfoPageDataModel =
                new PageDataModel(
                        sighUpInfoPage.getTotalElements(),
                        sighUpInfoPage.getTotalPages(),
                        sighUpInfoPage.getContent()
                );
        return new ResponseModel(sighUpInfoPageDataModel);
    }
}
