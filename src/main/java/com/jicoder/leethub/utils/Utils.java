package com.jicoder.leethub.utils;


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

}
