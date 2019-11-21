package com.qfjy.project.weixin.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/21 11:58
 */
@Data
public class TulingBean implements Serializable {
    //输入类型:0-文本(默认)、1-图片、2-音频
    private int reqType = 0;
    private Perception perception;
    private UserInfo userInfo;
}
