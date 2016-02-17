package com.weiwei.meilijia.er;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageService {
	public static byte[] getImage(String htmlpath) throws Exception {
		byte[] imagearray = null;    //定义一个字节数组
		URL url = new URL(htmlpath); //创建一个新的对象url
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		// conn.setRequestMethod("GET");
		conn.connect();
		if (conn.getResponseCode() == 200) {
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			InputStream inputStream = conn.getInputStream();
			//将文件内容写入位数组流
			while ((inputStream.read(buffer)) != -1) {
				arrayOutputStream.write(buffer, 0, buffer.length);
			}
			//以字符方式显示位数数组内容
			imagearray = arrayOutputStream.toByteArray();
		}
		return imagearray;
	}
}
