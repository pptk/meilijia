package com.example.meifushow;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
//ʵ�ֻص��������ࡣ
public class CallbackImpl implements AsynctaskImageLoad.ImageCallback{
	private ImageView imageView;
	public CallbackImpl (ImageView imageView){
		super();
		this.imageView = imageView;
	}
	@Override
	public void imageLoaded(Drawable imageDrawable) {
		// TODO Auto-generated method stub
		imageView.setImageDrawable(imageDrawable);
	}
}
