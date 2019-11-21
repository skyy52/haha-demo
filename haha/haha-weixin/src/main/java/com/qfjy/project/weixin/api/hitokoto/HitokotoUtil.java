package com.qfjy.project.weixin.api.hitokoto;


import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author skyy
 * @version 1.0 一言
 * @date 2019/11/21 19:19
 */

@Service
public class HitokotoUtil {
    private static final String HITOKOTO_API = "https://v1.hitokoto.cn/";

    public String sendMsg(){
        JSONObject obj = WeixinUtil.httpRequest(HITOKOTO_API, "GET", null);
        String hitokoto = (String) obj.get("hitokoto");
        return hitokoto;
    }

}
