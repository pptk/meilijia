package com.weiwei.meilijia.base;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/**
 * 网络请求
 * 
 * @author 仲丛旭
 * 
 */
public class HttpURLConnHelper {
	private final static String TAG = "MyHttpHelperUtil";

	/**
	 * 作用：实现网络访问文件，将获取到的数据存在字节数组中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return byte[]
	 */
	public static byte[] downLoadByteFromURL(String url) {
		Log.i(TAG, "==以get方式访问网络");
		HttpURLConnection httpConn = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		try {
			Log.i(TAG, "==1、创建URL对象，url-->" + url);
			URL urlObj = new URL(url);
			Log.i(TAG, "==2、创建连接，openConnection");
			httpConn = (HttpURLConnection) urlObj.openConnection();
			// 以下两项都是默认值。虽然可以不写，但是建议写上。
			httpConn.setRequestMethod("GET");
			// // 设置将服务器返回数据写入到httpConn对象
			// httpConn.setDoInput(true);
			// 设置访问超时时间
			httpConn.setConnectTimeout(1000);
			// // 设置是否使用缓存
			// httpConn.setUseCaches(false);
			// // 建立远程对象实际连接
			// httpConn.connect();
			Log.i(TAG,
					"==3、getResponseCode()方法获取服务器的返回码:"
							+ httpConn.getResponseCode());
			if (httpConn.getResponseCode() == 200) {
				Log.i(TAG,
						"==4、调用 HttpURLConnection对象的getInputStream()方法获取服务器返回的流信息");
				bis = new BufferedInputStream(httpConn.getInputStream());
				Log.i(TAG, "==5、执行标准的IO流操作");
				baos = new ByteArrayOutputStream();
				int c = 0;
				byte[] buffer = new byte[8 * 1024];
				while ((c = bis.read(buffer)) != -1) {
					baos.write(buffer, 0, c);
					baos.flush();
				}
				byte[] result = baos.toByteArray();
				Log.i(TAG, "result==" + result.length);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (baos != null) {
					baos.close();
				}
				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
