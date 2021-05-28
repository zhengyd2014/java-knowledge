package com.cloudkitchens.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    public static String currentTime() {
        return simpleDateFormat.format(new Date());
    }
}
