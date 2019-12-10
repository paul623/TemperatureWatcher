package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }
}
