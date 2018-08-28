package com.yoyogrape.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/6/12 0012
 * @desc 将申请人信息保存到本地txt文件中，文件名称CustomerInfo.txt
 */
@Component
public class SaveCustomerInfo {
    private static String customerUrl;
    @Value("${url.customerInfo}")
    public void setCustomerUrl(String customerUrl) {
        SaveCustomerInfo.customerUrl = customerUrl;
    }

    public static void insertCustomerInfo(String customInfo) {
        System.out.println("-----SaveCustomerInfo-----insertCustomerInfo------");
        //时间的获取
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());

        //文件路径
        String absolutePath ="";
        //如果properties文件中没有写路径那么存在当前目录下
        if (customerUrl == null || "default".equals(customerUrl)) {
            //获取当前路径
            absolutePath = new File("").getAbsolutePath();
        }else{
            absolutePath =customerUrl;
        }

        //新建file
        File file = new File(absolutePath + "\\CustomerInfo.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\r\n" + date + "\r\n" + customInfo
                    + "\r\n------------------------\r\n------------------------");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("申请人成功保持到CustomerInfo.txt中！");
    }
}
