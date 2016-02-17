package com.weiwei.meilijia.yi;

import java.util.ArrayList;
import java.util.List;

import com.example.yu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class pxhAdapter extends BaseAdapter {
	private Context context;
	private List<ShopInfo> list = new ArrayList<ShopInfo>();

	public pxhAdapter(Context context, List<ShopInfo> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		//加载布局管理器
		ShopInfo model = list.get(position);
		
			//将xml布局转换为view对象
			convertView = inflater.inflate(R.layout.pxh_shop_listview_item,
					null);
			//利用view对象，找到布局中的组件
			TextView goodsName = (TextView) convertView.findViewById(R.id.goodname);
			TextView goodsFrom = (TextView) convertView.findViewById(R.id.goodfrom);
			TextView introduce = (TextView) convertView.findViewById(R.id.introduce);
			TextView creaTime = (TextView) convertView.findViewById(R.id.creaTime);
			TextView nowPirce = (TextView) convertView.findViewById(R.id.nowpirce);
			TextView number = (TextView)convertView.findViewById(R.id.number);
			ImageView picture = (ImageView)convertView.findViewById(R.id.pic);
			
			goodsName.setText(model.getGoodsName());
			goodsFrom.setText(model.getGoodsFrom());
			introduce.setText(model.getIntroduce());
			creaTime.setText(model.getCreaTime());
			nowPirce.setText(model.getNowPirce());
			number.setText(String.valueOf(model.getNumber())+"");
			Bitmap bit = BitmapFactory.decodeByteArray(model.getPicture(), 0, model.getPicture().length); //将图片流转化为bitmap类型 
			picture.setImageBitmap(bit);
			return convertView;
		}
}
