package com.yoyogrape.tools;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/6/15 0015
 * @desc 获取AccessToken
 */
@Component
public class WeChatGetAccessToken {
    private static String getAccessToken_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";
    private static String corpid;
    private static String corpsecret;

    @Value("${wechat.corpid}")
    public void setCorpid(String corpid) {
        WeChatGetAccessToken.corpid = corpid;
    }

    @Value("${wechat.corpsecret}")
    public void setCorpsecret(String corpsecret) {
        WeChatGetAccessToken.corpsecret = corpsecret;
    }

    public static JSONObject getAccessToken() {
        System.out.println("----WeChatGetAccessToken-----getAccessToken----");
        getAccessToken_url = getAccessToken_url.replace("APPID", corpid)
                .replace("APPSECRET", corpsecret);
        return WeChatRequest.httpRequest(getAccessToken_url, "GET", null);
    }
}
