package com.qfjy.project.weixin.api.tuling;

import com.qfjy.project.weixin.api.tuling.bean.InputText;
import com.qfjy.project.weixin.api.tuling.bean.Perception;
import com.qfjy.project.weixin.api.tuling.bean.TulingBean;
import com.qfjy.project.weixin.api.tuling.bean.UserInfo;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/21 11:43
 */
@Service
public class TulingUtil {

    private static final String TULING_API_URL = "http://openapi.tuling123.com/openapi/api/v2";

    private int count = 0;//记录机器人编号

    /**
     * 按规则生成机器人接收的数据
     *
     * @param msg    用户发送的文本
     * @param apikey 机器人的key
     * @return
     */
    public JSONObject sendJSONObject(String msg, String apikey) {
        System.out.println(msg);
        InputText inputText = new InputText();
        inputText.setText(msg);
        Perception perception = new Perception();
        perception.setInputText(inputText);

        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey(apikey);
        userInfo.setUserId("java1903");//随意写 唯一即可

        TulingBean tulingBean = new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject = JSONObject.fromObject(tulingBean);
        return jsonObject;

    }

    /**
     * 发送消息给机器人并响应用户信息
     *
     * @param msg 用户发送的消息
     * @return 响应用户的信息
     */
    public String sendMessage(String msg) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
        //零点归零
        if (simpleDateFormat.format(date).equals("00-00-00")) {
            count = 0;
        }

        if (count < 5) {
            String apikey = getApikey(count);
            JSONObject jsonObject = sendJSONObject(msg, apikey);

            //向机器人发送post请求
            JSONObject object = WeixinUtil.httpRequest(TULING_API_URL, "POST", jsonObject.toString());
            JSONArray array = (JSONArray) object.get("results");

            //System.out.println(array.toString());
            JSONObject object1 = (JSONObject) array.get(0);
            JSONObject object2 = (JSONObject) object1.get("values");
            String result = (String) object2.get("text");
            //System.out.println(result);

            if (result.equals("请求次数超限制!")) {
                synchronized (Class.class) {
                    count++;
                }
                /*
                    没有return被坑了一天
                 */
                return sendMessage(msg);
            }
            return result;
        } else {
            return "服务器正忙,请稍后重试...";
        }
        //return "请稍后重试...";
    }

    public String getApikey(int count) {

        String[] apikey = {"b9ec3dadbc75408e93ba2d5b7e6ceee9", "5a1265359da440dda8f58c68bc20c07d",
                "0e6c333b51e04d2695f0efd5ce745284", "79562d008dfb467aaeb0dd5abda50e13",
                "c5bd6f57c9c949c69bc7018320338405"};
        return apikey[count];
    }
}
