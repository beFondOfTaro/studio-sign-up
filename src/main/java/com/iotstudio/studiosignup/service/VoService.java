package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface VoService {

    ResponseModel getUserStudentInfo(Integer userId);
}