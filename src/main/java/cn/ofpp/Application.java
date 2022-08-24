package cn.ofpp;

import cn.ofpp.core.BoyFriend;
import cn.ofpp.core.GirlFriend;
import cn.ofpp.core.MessageFactory;
import cn.ofpp.core.Wx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 启动类
 *
 * 这个理论上只能用测试号 正式的号 个人认证是不支持模板消息的 企业认证的又必须使用微信模板库里的模板 只有测试的可以自定义模板内容
 * <a href="https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index">申请公众号测试应用地址</a>
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Application {

    /**
     * <li>创建配置</li>
     * <li>创建几个 男/女 朋友</li>
     * <li>发消息</li>
     */
    public static void main(String[] args) {
        // load and init
        Bootstrap.init();

        // -----------------  以下为演示数据  ------------------------


        String xiaofei = "o_st-57nd9HXV8Br8ZbloAvCVpS8";
        String my = "o_st-5_rt_OAIBr3S6dPeECurmOc";

        // new 一个 臭猪
        GirlFriend girlFriend = new GirlFriend(getRandomName(),
                "湖北省", "武汉市", "1998-09-27", "2020-01-05", my);
        Wx.sendTemplateMessage(MessageFactory.resolveMessage(girlFriend));


    }

    /**
     * 获取臭猪随机称呼
     * @return
     */
    public static String getRandomName(){

        int index = (int)(Math.random()*3);

        String[] LovelyNikeName = {"亲爱的小费老师!","最爱的宝贝猪!","正在努力的臭宝!","每天放屁的航航!"};

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

    // new 一个 男友 也可单独针对一个friend设置模板ID 以达到不同人不同消息  自定义模板
//        BoyFriend boyFriend = new BoyFriend("某男友",
//                "江苏省", "南京市", "1999-08-08", "2011-04-16", "oQFk-5qtXv2uGNCu9oiCiV85KWD8", "5t7-Ksy8_rw-QmUkxf8J7Pe-QLQ2rBc7RWJi_pSmeh4");
//        Wx.sendTemplateMessage(MessageFactory.resolveMessage(boyFriend));

//    {{friendName.DATA}}
//    你今年{{howOld.DATA}}岁啦
//    距下一次阳历生日还有{{nextBirthday.DATA}}天
//    距我们的下一次纪念日仅有{{nextMemorialDay.DATA}}天
//    你当前在{{province.DATA}}{{city.DATA}}
//    天气大概率是:{{weather.DATA}}  {{temperature.DATA}
//        {{winddirection.DATA}}  {{windpower.DATA}}
//        空气湿度:{{humidity.DATA}}
//        {{weatherTips.DATA}}
//        {{author.DATA}}
//        {{origin.DATA}}
//        {{content.DATA}}

//{{friendName.DATA}}
//你今年{{howOld.DATA}}岁啦
//距下一次阳历生日还有{{nextBirthday.DATA}}天
//距我们的下一次纪念日仅有{{nextMemorialDay.DATA}}天
//你当前在{{province.DATA}}{{city.DATA}}
//天气大概率是:{{weather.DATA}}
//温度{{temperature.DATA}
//湿度{{winddirection.DATA}}
//甜腻{{windpower.DATA}}
//空气湿度:{{humidity.DATA}}
//{{weatherTips.DATA}}
//{{content.DATA}}

}
