//package com.yoyogrape.wechat.service;
//
//import com.yoyogrape.wechat.pojo.BaseMessage;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServlet;
//
///**
// * @author songjn@yoyogrape.com
// * @date 2018/6/14 0014
// * @desc 发送消息
// */
//public class SendMessageService {
//    private static Logger log = LoggerFactory.getLogger(UserTest.class);
//
//    private  static  String sendMessage_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
//
//    /**
//     * @desc ：0.公共方法：发送消息
//     *
//     * @param accessToken
//     * @param message void
//     */
//    public void sendMessage(String accessToken,BaseMessage message){
//
//        //1.获取json字符串：将message对象转换为json字符串
//        Gson gson = new Gson();
//        String jsonMessage =gson.toJson(message);      //使用gson.toJson(user)即可将user对象顺序转成json
//        System.out.println("jsonTextMessage:"+jsonMessage);
//
//
//        //2.获取请求的url
//        sendMessage_url=sendMessage_url.replace("ACCESS_TOKEN", accessToken);
//
//        //3.调用接口，发送消息
//        JSONObject jsonObject = WeiXinUtil.httpRequest(sendMessage_url, "POST", jsonMessage);
//        System.out.println("jsonObject:"+jsonObject.toString());
//
//        //4.错误消息处理
//        if (null != jsonObject) {
//            if (0 != jsonObject.getInt("errcode")) {
//                log.error("创建成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
//            }
//        }
//    }
//
//
//
//
//}