package com.iotstudio.studiosignup.util;

import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class BindingResultHandlerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BindingResultHandlerUtil.class);

    public static ResponseModel onError(BindingResult bindingResult){
        List<String> msgList = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()){
            String msg = error.getDefaultMessage();
            LOGGER.info(msg);
            msgList.add(msg);
        }
        return new ResponseModel(msgList.toString());
    }
}
