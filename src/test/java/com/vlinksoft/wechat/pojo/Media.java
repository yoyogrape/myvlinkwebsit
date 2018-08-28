package com.yoyogrape.wechat.pojo;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/6/14 0014
 * @desc 图片、语音、文件
 */

public class Media {
    //是     图片/语音/文件 媒体文件id，可以调用上传临时素材接口获取
    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}