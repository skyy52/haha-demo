package com.qfjy.project.weixin.api.tuling;

import com.qfjy.project.weixin.api.tuling.bean.InputText;
import com.qfjy.project.weixin.api.tuling.bean.Perception;
import com.qfjy.project.weixin.api.tuling.bean.TulingBean;
import com.qfjy.project.weixin.api.tuling.bean.UserInfo;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/21 11:43
 */
@Service
public class TulingUtil {
    private static final String TULING_API_URL = "http://openapi.tuling123.com/openapi/api/v2";

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
        JSONObject jsonObject = sendJSONObject(msg, "8473f2fd011945e9810424cc205e1e5c");
        //向机器人发送post请求
        JSONObject object = WeixinUtil.httpRequest(TULING_API_URL, "POST", jsonObject.toString());
        JSONArray array = (JSONArray) object.get("results");

        JSONObject object1 = (JSONObject) array.get(0);
        JSONObject object2 = (JSONObject) object1.get("values");
        String result= (String) object2.get("text");
        return result;
    }
}
