package com.yanrui.demo.utils;

import java.util.Random;

/**
 * @author 许睿
 * @version 1.0
 * @description
 * @date 2020/9/16 0:16
 */
public class KeyUtil {
    /**
     * 生成唯一的主键,当前时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
