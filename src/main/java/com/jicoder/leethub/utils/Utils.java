package com.jicoder.leethub.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    /**
     * List翻转工具
     * @param list
     * @param <T>
     */
    public static <T> void reverseList(List<T> list){
        int size = list.size();
        int i = 0, j = size - 1;
        while(i < j){
            T t = list.get(i);
            list.set(i, list.get(j));
            list.set(j, t);
            i++;
            j--;
        }
    }

    public static boolean isToday(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }


}
