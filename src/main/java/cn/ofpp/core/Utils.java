package cn.ofpp.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *@filename: Utils.java
 *@Describe:
 *@Author: Cole.zhou
 *@Date: 2022-08-24 10:32
 */
public class Utils {


    /**
     * 获取臭猪随机称呼
     * @return
     */
    public static String getRandomName(){

        int index = (int)(Math.random()*5);

        String[] LovelyNikeName = {"亲爱的小费老师!","最爱的宝贝猪!","正在努力的臭宝!","每天放屁的航航!","可爱动人的臭猪"};

        return LovelyNikeName[index];

    }



    public static String getTodayOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        String format = new SimpleDateFormat("yyyy年MM月dd").format(dt);
        return format +" " + weekDays[w];
    }



}
