package com.yoyogrape.controller;

import com.yoyogrape.po.MailObj;
import com.yoyogrape.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/5/29
 * @desc 发送邮件的Controller
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/sendSimpleMail", method = RequestMethod.POST)
    @ResponseBody
    public Map sendSimpleMail(@RequestBody MailObj mailObj) {
        System.out.println("----MailController----sendSimpleMail----");
        return mailService.sendSimpleMail(mailObj);
    }
}