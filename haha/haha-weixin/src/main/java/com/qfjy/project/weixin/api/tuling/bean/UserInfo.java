package com.qfjy.project.weixin.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/21 11:59
 */
@Data
public class UserInfo implements Serializable {
    //机器人标识
    private String apiKey;
    //用户唯一标识
    private String userId;
}
