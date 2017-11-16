package com.iotstudio.studiosignup.util;

import java.text.SimpleDateFormat;

public class Date extends java.util.Date{
    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(this);
    }
}
