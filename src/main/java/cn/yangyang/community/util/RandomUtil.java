package cn.yangyang.community.util;

import java.util.Random;

public class RandomUtil {
    //生成随机六位字符串
    public static String RandomVerificationCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
