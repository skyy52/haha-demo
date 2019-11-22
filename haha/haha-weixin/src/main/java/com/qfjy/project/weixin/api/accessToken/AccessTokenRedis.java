package com.qfjy.project.weixin.api.accessToken;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/22 20:39
 */
@Service
public class AccessTokenRedis {
    private static final String ACCESS_TOKEN_KEY="access_token";
    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    /**
     * 先去redis查询key
     * 存在则get 后返回
     * 不存在则请求微信获取accesstoken的值
     * 并存入redis中  设置过期时间
     * @return 返回accesstoken的值
     */
    public String getAccessToken(){
        if(redisTemplate.hasKey(ACCESS_TOKEN_KEY)){
            String token=(String) redisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
            System.out.println("redis查到的token"+token);
            return token;
        }
        // 不存在的话请求微信服务器
        String token = getAccess_token();
        redisTemplate.opsForValue().set(ACCESS_TOKEN_KEY,token, 2,TimeUnit.HOURS);
        System.out.println("微信返回的token"+token);
        return token;
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
