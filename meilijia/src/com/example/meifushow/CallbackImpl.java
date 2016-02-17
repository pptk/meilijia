package com.example.meifushow;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
//实现回调函数的类。
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
