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
	 * ͼƬ�������
	 * url��ӦsoftReference�����ͼƬ����
	 */
	private Map<String,SoftReference<Drawable>> imageCache = 
			new HashMap<String,SoftReference<Drawable>>();
	/**
	 * ʵ��ͼƬ�첽���أ�
	 * String ͼƬurl��ַ
	 * ImageCallback�ص���������
	 */
	public Drawable loadDrawable(final String imageUrl,final ImageCallback callback ){
		if(imageCache.containsKey(imageUrl)){
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			if(softReference.get() != null){
				return softReference.get();//��Ϊ�ա�����softָ���ͼƬ����
			}
		}
		final Handler handler = new Handler(){
			public void handleMessage(Message msg) {
				callback.imageLoaded((Drawable) msg.obj);
			};
		};
		new Thread(){
			public void run(){
				Drawable drawable = loadImageFromUrl(imageUrl);//��������һ��ͼƬ��
				imageCache.put(imageUrl,new SoftReference<Drawable>(drawable));//�ŵ����档
				Message message = handler.obtainMessage(0,drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}
	
	/**
	 * ͼƬ���ط�����
	 * @param imageUrl
	 * @return
	 */
	protected Drawable loadImageFromUrl(String imageUrl){
		try{
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");//����һ��ͼƬ��
		}catch (Exception e) {
//			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.add);
//			Drawable able = new BitmapDrawable(bitmap);
//			return able;//��������쳣���ͷ��ر���ͼƬadd��
			throw new RuntimeException();//��
		}
	}
	/**
	 * �ص����������ú��������õ�ʱ��ʵ�ִ˷��������ҽ��˷�����Ϊ��������loadDrawable
	 * @author Administrator
	 */
	public interface ImageCallback{
		public void imageLoaded(Drawable imageDrawable);
	}
	/**
	 * ��Activity�� ����дgetResources����
	 * @return
	 */
//	private Resources getResources() {
//		// TODO Auto-generated method stub
//		Resources mResources = null;
//		mResources = getResources();
//		return mResources;
//		}
}
