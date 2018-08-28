package com.yoyogrape.tools;


import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/6/15 0015
 * @desc 封装的微信推送请求WeChatRequest工具类
 */
public class WeChatRequest {
    public static JSONObject httpRequest(String URL, String method, String param) {
        if ("GET".equals(method)) {
            return sendGet(URL, param);
        } else if ("POST".equals(method)) {
            return sendPost(URL, param);
        } else {
            return JSONObject.fromObject("method参数是GET或者POST");
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static JSONObject sendGet(String url, String param) {
        System.out.println("------WeChatRequest--------sendGet------");
        JSONObject jsonObject = null;
        try {
            URL conn_url = new URL(url);
            HttpURLConnection conn = (HttpsURLConnection) conn_url.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.connect();
            //output获取access_token是不会用到
            if (param != null) {
                OutputStream outputstream = conn.getOutputStream();
                //字符集，防止出现中文乱码
                outputstream.write(param.getBytes("UTF-8"));
                outputstream.close();
            }
            //正常返回代码为200
            if (conn.getResponseCode() == 200) {
                InputStream stream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(stream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                StringBuffer buffer = new StringBuffer();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                stream.close();
                conn.disconnect();
                jsonObject = JSONObject.fromObject(buffer.toString());
                if ("ok".equals(jsonObject.getString("errmsg"))) {
                    System.out.println("获取Access_Token成功！");
                } else {
                    System.out.println("获取Access_Token失败！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param requestUrl 发送请求的 URL
     * @param param      请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static JSONObject sendPost(String requestUrl, String param) {
        System.out.println("------WeChatRequest--------sendPost------");
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("POST");

            // 当有数据需要提交时
            if (null != param) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());

            if ("ok".equals(jsonObject.getString("errmsg"))) {
                System.out.println("微信推送成功！");
            } else {
                System.out.println("微信推送失败！");
            }
//            System.out.println("最后请求的返回：" + jsonObject.toString());

        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}" + e);
        }
        return jsonObject;
    }
}
