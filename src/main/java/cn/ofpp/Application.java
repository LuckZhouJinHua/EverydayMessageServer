package cn.ofpp;

import cn.ofpp.core.BoyFriend;
import cn.ofpp.core.GirlFriend;
import cn.ofpp.core.MessageFactory;
import cn.ofpp.core.Wx;

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


    // new 一个 男友 也可单独针对一个friend设置模板ID 以达到不同人不同消息  自定义模板
//        BoyFriend boyFriend = new BoyFriend("某男友",
//                "江苏省", "南京市", "1999-08-08", "2011-04-16", "oQFk-5qtXv2uGNCu9oiCiV85KWD8", "5t7-Ksy8_rw-QmUkxf8J7Pe-QLQ2rBc7RWJi_pSmeh4");
//        Wx.sendTemplateMessage(MessageFactory.resolveMessage(boyFriend));





// {{friendName.DATA}}
//你今年{{howOld.DATA}}岁啦(如果你不喜欢看到这,我将在下次更新去掉!!请积极反馈)
//距下一次阳历生日还有{{nextBirthday.DATA}}天
//距我们的下一次纪念日仅有{{nextMemorialDay.DATA}}天
//你当前老巢在{{province.DATA}}{{city.DATA}}
//今天天气大概率是{{weather.DATA}}
//{{weatherTips.DATA}}
//今日气温{{temperature.DATA}}℃
//风力描述{{winddirection.DATA}}
//风力级别{{windpower.DATA}}级
//空气湿度{{humidity.DATA}}
//{{author.DATA}}
//{{origin.DATA}}
//{{content.DATA}}

}
