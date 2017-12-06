package com.iotstudio.studiosignup.enums;

public enum SighUpInfoCheckCode {
    UNCHECKED(1),
    PASSED(2),
    FAILED(3);

    private Integer value;

    SighUpInfoCheckCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
