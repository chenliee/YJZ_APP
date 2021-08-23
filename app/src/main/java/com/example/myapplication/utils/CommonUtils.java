package com.example.myapplication.utils;

public class CommonUtils {
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    public static  boolean isPhone(String phone){
        if (phone.matches(REGEX_MOBILE)){
            return true;
        }else {
            return false;
        }
    }
}
