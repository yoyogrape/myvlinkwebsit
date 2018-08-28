package com.yoyogrape.wechat.pojo;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/6/14 0014
 * @desc 文本消息
 */

public class TextMessage extends BaseMessage {
    //文本
    private Text text;
    //否     表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;

    public Text getText() {
        return text;
    }
    public void setText(Text text) {
        this.text = text;
    }
    public int getSafe() {
        return safe;
    }
    public void setSafe(int safe) {
        this.safe = safe;
    }

}