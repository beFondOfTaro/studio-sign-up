package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface BaseService<T> {

    ResponseModel addOne(T t);

    ResponseModel deleteOneById(Integer id);

    ResponseModel updateOne(T t);

    ResponseModel selectOneById(Integer id);

    ResponseModel selectAll();

    ResponseModel selectAllByPage(Integer page,Integer size);
}
