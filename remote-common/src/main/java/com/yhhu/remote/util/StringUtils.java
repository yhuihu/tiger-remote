package com.yhhu.remote.util;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 工具类
 */
public class StringUtils {

    /**
     * is empty string.
     *
     * @param str source string.
     * @return is empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * is not empty string.
     *
     * @param str source string.
     * @return is not empty.
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
