package cn.ofpp.core;

import cn.hutool.core.util.StrUtil;
import cn.ofpp.Bootstrap;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.ofpp.Application.getTodayOfDate;
import static cn.ofpp.core.GaodeUtil.getAdcCode;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class MessageFactory {

    public static WxMpTemplateMessage resolveMessage(Friend friend) {
        return WxMpTemplateMessage.builder()
                .url("https://ofpp.cn") // 点击后的跳转链接 可自行修改 也可以不填
                .toUser(friend.getUserId())
                .templateId(StrUtil.emptyToDefault(friend.getTemplateId(), Bootstrap.TEMPLATE_ID))
                .data(buildData(friend))
                .build();
    }

    /**
     *
     * {@code {{xxxx.DATA}}} xxxx就是一个变量名，消息中设置变量 然后传递时传递变量即可
     * <br/>
     * 色彩取值可以从这里挑选 https://arco.design/palette/list
     *
     *  <p>
     *      你叫{{friendName.DATA}}
     *      今年{{howOld.DATA}}
     *      距离下一次生日{{nextBirthday.DATA}}天
     *      具体我们的下一次纪念日{{nextMemorialDay.DATA}}天
     *      现在在{{province.DATA}}{{city.DATA}}
     *      当前天气{{weather.DATA}}
     *      当前气温{{temperature.DATA}}
     *      风力描述{{winddirection.DATA}}
     *      风力级别{{windpower.DATA}}
     *      空气湿度{{humidity.DATA}}
     *      {{author.DATA}}
     *      {{origin.DATA}}
     *      {{content.DATA}}
     *  </p>
     */
    private static List<WxMpTemplateData> buildData(Friend friend) {
        WeatherInfo weather = GaodeUtil.getNowWeatherInfo(getAdcCode(friend.getProvince(), friend.getCity()));
        RandomAncientPoetry.AncientPoetry ancientPoetry = RandomAncientPoetry.getNext();

        ArrayList<WxMpTemplateData> wxMpTemplateData = new ArrayList<>();
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("friendName").value(friend.getFullName() + " 早上好").color("#F53F3F").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("todayDate").value(Utils.getTodayOfDate(new Date())).color("#F77234").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("howOld").value(friend.getHowOld().toString()).color("#FF9A2E").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("howLongLived").value(friend.getHowLongLived()).color("#F9CC45").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("nextBirthday").value(friend.getNextBirthdayDays()).color("#FADC19").build());

        wxMpTemplateData.add(TemplateDataBuilder.builder().name("nextMemorialDay").value(friend.getNextMemorialDay()).color("#9FDB1D").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("province").value(friend.getProvince()).color("#4CD263").build());
        wxMpTemplateData.add( TemplateDataBuilder.builder().name("city").value(friend.getCity()).color("#4CD263").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("weather").value(weather.getWeather()).color("#37D4CF").build());

        wxMpTemplateData.add(TemplateDataBuilder.builder().name("temperature").value(weather.getTemperature()+"℃").color("#37D4CF").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("winddirection").value(weather.getWinddirection()+"风").color("#57A9FB").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("windpower").value(weather.getWindpower()+"级").color("#57A9FB").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("humidity").value(weather.getHumidity()+"%").color("#4080FF").build());

        /*特殊天气提示*/
        if(weather.getWeather().contains("云") || weather.getWeather().contains("晴")){
            wxMpTemplateData.add(TemplateDataBuilder.builder().name("weatherTips").value("今日天气感觉不错,要保持好心情哦!").color("#8D4EDA").build());
        }else if(weather.getWeather().contains("雨")){
            wxMpTemplateData.add(TemplateDataBuilder.builder().name("weatherTips").value("今日可能有雨,记得带伞哦,宝!").color("#8D4EDA").build());
        }else if(weather.getWeather().contains("雪")){
            wxMpTemplateData.add(TemplateDataBuilder.builder().name("weatherTips").value("宝贝!下雪啦,要穿暖和出门看你喜欢的雪花!").color("#8D4EDA").build());
        }
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("author").value(ancientPoetry.getAuthor()).color("#4e5969").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("origin").value(ancientPoetry.getOrigin()).color("#4e5969").build());
        wxMpTemplateData.add(TemplateDataBuilder.builder().name("content").value(ancientPoetry.getContent()).color("#4e5969").build());
        return wxMpTemplateData;
    }

    static class TemplateDataBuilder {
        private String name;
        private String value;
        private String color;

        public static TemplateDataBuilder builder() {
            return new TemplateDataBuilder();
        }
        public TemplateDataBuilder name(String name) {
            this.name = name;
            return this;
        }
        public TemplateDataBuilder value(String value) {
            this.value = value;
            return this;
        }
        public TemplateDataBuilder color(String color) {
            this.color = color;
            return this;
        }
        public WxMpTemplateData build() {
            if (StrUtil.hasEmpty(name, value)) {
                throw new IllegalArgumentException("参数不正确");
            }
            WxMpTemplateData data = new WxMpTemplateData();
            data.setName(name);
            data.setValue(value);
            data.setColor(color);
            return data;
        }
    }

}
