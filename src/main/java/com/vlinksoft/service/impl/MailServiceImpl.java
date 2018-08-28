package com.yoyogrape.service.impl;

import com.yoyogrape.po.MailObj;
import com.yoyogrape.service.MailService;
import com.yoyogrape.tools.SaveCustomerInfo;
import com.yoyogrape.tools.WeChatSendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    //从propertise文件中获取发送邮箱和接收邮箱的信息
    @Value("${mail.fromMail.addr}")
    private String from;
    @Value("${mail.toMail.addr}")
    private String to;
    @Value("${mail.toMail.subject}")
    private String subject;

    /**
     * @author songjn@yoyogrape.com
     * @date 2018/5/29
     * @desc 发送邮件serviceimpl
     */

    @Override
    public Map sendSimpleMail(MailObj mailObj) {
        System.out.println("-------MailServiceImpl-------sendSimpleMail-----");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        String orderProduct_str = "";
        String getOrderProductArr[] = mailObj.getOrderProducts();

        switch (getOrderProductArr.length) {
            case 1:
                if ("1".equals(getOrderProductArr[0])) {
                    orderProduct_str = "视频监控平台";
                } else {
                    orderProduct_str = "ITSM办公流程平台";
                }
                break;
            case 2:
                orderProduct_str = "视频监控平台、ITSM办公流程平台";
                break;
        }

        String content = "\n产品预约演示申请：\n\n公司名称：" + mailObj.getConpanyName() + "\n联系人：" + mailObj.getConpanyCustomer()
                + "\n联系电话：" + mailObj.getPhoneNum() + "\n预约产品：" + orderProduct_str + "\n\n请及时进行联系！";

        message.setText(content);

        //1、微信推送
        WeChatSendMsg.sendMsg(content);

        //2、将申请人信息保存到本地文件中
        SaveCustomerInfo.insertCustomerInfo(content);

        //将返回的信息封装到map集合中
        Map map = new HashMap();
        try {
            //3、发送邮件
            mailSender.send(message);
            System.out.println("邮件发送成功！");
            map.put("state", 200);
            map.put("msg", "您的预约申请已经提交，稍后我们会及时与您联系。");
        } catch (Exception e) {
            map.put("state", 299);
            map.put("msg", "请仔细核对填写信息是否正确或稍后重试。");
            System.out.println("邮件发送失败！");
        }
        return map;
    }
}
