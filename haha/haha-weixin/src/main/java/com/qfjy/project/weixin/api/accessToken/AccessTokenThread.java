package com.qfjy.project.weixin.api.accessToken;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;


/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/22 19:16
 */
public class AccessTokenThread extends Thread {

    // accesstoken的值
    public static String access_token;
    @Override
    public void run() {
        while (true){
            try {
                access_token=getAccess_token();
                System.out.println("得到的accesstoken:"+access_token);
                //每隔2小时重新获取一次 7200*1000
                Thread.sleep(7000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 向微信发送请求获取accesstoken
    private String getAccess_token(){

        String url =ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject object = WeixinUtil.httpRequest(url, "GET", null);
        String access_token = (String) object.get("access_token");
        return access_token;

    }

}
