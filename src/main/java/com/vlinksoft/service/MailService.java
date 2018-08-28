package com.yoyogrape.service;

import com.yoyogrape.po.MailObj;
import java.util.Map;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/5/29 0029
 */
public interface MailService {
    Map sendSimpleMail(MailObj mailObj);
}
