package com.yoyogrape.tools;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/6/15 0015
 * @desc 微信消息推送
 */
@Component
public class WeChatSendMsg {
    private static String sendMessage_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
    private static Long agentid;
    private static String testUser;

    @Value("${wechat.agentid}")
    public void setAgentid(Long agentid) {
        WeChatSendMsg.agentid = agentid;
    }

    @Value("${wechat.testUser}")
    public void setAgentid(String testUser) {
        WeChatSendMsg.testUser = testUser;
    }

    public static void sendMsg(String content) {
         String postJson = "{\"agentid\":" + agentid + ",\"touser\": \"" + testUser + "\",\"toparty\": \"\",\"totag\": \"\"," +
                "\"msgtype\":\"text\",\"text\": {\"content\": \"" + content + "\"},\"safe\":0}";
        //获取AccessToken
        JSONObject accessToken = WeChatGetAccessToken.getAccessToken();
        sendMessage_url = sendMessage_url.replace("ACCESS_TOKEN", accessToken.getString("access_token").toString());
        //企业微信接口请求
        WeChatRequest.httpRequest(sendMessage_url, "POST", postJson);
    }
}
