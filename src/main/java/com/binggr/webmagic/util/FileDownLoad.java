package com.binggr.webmagic.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载文件工具类
 * @author: bing
 * @date: 2020/12/21 23:25
 */
public class FileDownLoad {
    public static void downloads(String urlStr, String fileName, String location) throws IOException {
        URL url = new URL(urlStr);
        //打开url连接
        URLConnection urlConnection = url.openConnection();
        //请求超时时间
        urlConnection.setConnectTimeout(5000);
        //输入流
        InputStream in = urlConnection.getInputStream();
        //缓冲数据
        byte[] bytes = new byte[1024];
        //数据长度
        int len;
        //文件
        File file = new File(location);
        if (!file.exists()) {
            file.mkdirs();
        }
        //输出文件地址
        OutputStream out = new FileOutputStream(file.getPath() + "\\" + fileName);
        //先读到bytes中
        while ((len = in.read(bytes)) != -1) {
            //再从bytes中写入文件
            out.write(bytes, 0, len);
        }
        System.out.println("文件下载完毕!");
        //关闭IO
        out.close();
        in.close();
    }

}
