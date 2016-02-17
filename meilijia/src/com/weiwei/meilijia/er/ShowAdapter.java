package com.weiwei.meilijia.er;

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

	public ShowAdapter(Context context, List<ShowInfo> list) {
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

		username.setText(list.get(position).getName());
		createTime.setText(list.get(position).getTime());
		xinde.setText(list.get(position).getContent());
	    loadImage(list.get(position).getImage1(), one);
		loadImage(list.get(position).getImage2(), two);
		loadImage(list.get(position).getImage3(), three);
		return convertView;
	}

	private void loadImage(String url, ImageView imageView) {
		// ʵ�������ûص��ӿڵ��ࡣ
		CallbackImpl callbackImpl = new CallbackImpl(imageView);// ʵ�����ص���������
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);// ִ���첽���ط���
		if (cacheImage != null) {// �������������ڡ���ֱ����ʾ��
			imageView.setImageDrawable(cacheImage);
		}
	}
}
