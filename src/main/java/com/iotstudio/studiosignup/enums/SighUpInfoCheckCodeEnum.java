package com.iotstudio.studiosignup.enums;

public enum SighUpInfoCheckCodeEnum {
    UNCHECKED(1),
    PASSED(2),
    FAILED(3);

    private Integer value;

    SighUpInfoCheckCodeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
