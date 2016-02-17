package com.example.meifushow;

import java.util.List;

import com.example.yu.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowAdapter extends BaseAdapter {
	private List<ShowInfo> list;
	private Context context;
	private AsynctaskImageLoad loader = new AsynctaskImageLoad();

	public ShowAdapter(List<ShowInfo> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater iflater = LayoutInflater.from(context);
			convertView = iflater.inflate(R.layout.show_item, null);
		}
		TextView username = (TextView) convertView.findViewById(R.id.username);
		TextView createTime = (TextView) convertView
				.findViewById(R.id.createTime);
		TextView xinde = (TextView) convertView.findViewById(R.id.xinde);
		ImageView one = (ImageView) convertView.findViewById(R.id.one);
		ImageView two = (ImageView) convertView.findViewById(R.id.two);
		ImageView three = (ImageView) convertView.findViewById(R.id.three);

		username.setText(list.get(position).getUsername());
		createTime.setText(list.get(position).getCreateTime());
		xinde.setText(list.get(position).getXinde());
	    loadImage("http://192.168.2.115:8080/meilijia3/"+list.get(position).getOne(), one);
		loadImage("http://192.168.2.115:8080/meilijia3/"+list.get(position).getTwo(), two);
		loadImage("http://192.168.2.115:8080/meilijia3/"+list.get(position).getThree(), three);
		return convertView;
	}

	private void loadImage(String url, ImageView imageView) {
		// 实例化调用回调接口的类。
		CallbackImpl callbackImpl = new CallbackImpl(imageView);// 实例化回调函数对象
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);// 执行异步加载方法
		if (cacheImage != null) {// 如果缓存里面存在。则直接显示。
			imageView.setImageDrawable(cacheImage);
		}
	}
}
