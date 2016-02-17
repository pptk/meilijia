package com.example.meifushow;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsynctaskImageLoad {
	/**
	 * 图片缓存对象
	 * url对应softReference管理的图片对象。
	 */
	private Map<String,SoftReference<Drawable>> imageCache = 
			new HashMap<String,SoftReference<Drawable>>();
	/**
	 * 实现图片异步加载，
	 * String 图片url地址
	 * ImageCallback回调函数。。
	 */
	public Drawable loadDrawable(final String imageUrl,final ImageCallback callback ){
		if(imageCache.containsKey(imageUrl)){
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			if(softReference.get() != null){
				return softReference.get();//不为空、返回soft指向的图片对象
			}
		}
		final Handler handler = new Handler(){
			public void handleMessage(Message msg) {
				callback.imageLoaded((Drawable) msg.obj);
			};
		};
		new Thread(){
			public void run(){
				Drawable drawable = loadImageFromUrl(imageUrl);//用来下载一张图片、
				imageCache.put(imageUrl,new SoftReference<Drawable>(drawable));//放到缓存。
				Message message = handler.obtainMessage(0,drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}
	
	/**
	 * 图片下载方法。
	 * @param imageUrl
	 * @return
	 */
	protected Drawable loadImageFromUrl(String imageUrl){
		try{
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");//返回一个图片流
		}catch (Exception e) {
//			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.add);
//			Drawable able = new BitmapDrawable(bitmap);
//			return able;//如果下载异常、就返回本地图片add。
			throw new RuntimeException();//。
		}
	}
	/**
	 * 回调函数。调用函数，调用的时候实现此方法。并且将此方法作为参数传给loadDrawable
	 * @author Administrator
	 */
	public interface ImageCallback{
		public void imageLoaded(Drawable imageDrawable);
	}
	/**
	 * 非Activity类 。重写getResources方法
	 * @return
	 */
//	private Resources getResources() {
//		// TODO Auto-generated method stub
//		Resources mResources = null;
//		mResources = getResources();
//		return mResources;
//		}
}
