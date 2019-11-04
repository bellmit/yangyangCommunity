package cn.yangyang.community.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ce {
    public static void main(String[] args) {
        String str= UUID.randomUUID().toString();
        System.out.println(str.replace("-","").length());
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(date));
    }
}
