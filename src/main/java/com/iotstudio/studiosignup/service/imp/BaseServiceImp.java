package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.service.BaseService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImp<JpaRepository> implements BaseService<JpaRepository> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImp.class);

    @Autowired
    private JpaRepository jpaRepository;

    @Override
    public ResponseModel addOne(JpaRepository jpaRepository) {
        return new ResponseModel(jpaRepository);
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        return null;
    }

    @Override
    public ResponseModel updateOne(JpaRepository jpaRepository) {
        return null;
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return null;
    }

    @Override
    public ResponseModel selectAll() {
        return null;
    }

    @Override
    public ResponseModel selectAllByPage(Integer page, Integer size) {
        return null;
    }
}
