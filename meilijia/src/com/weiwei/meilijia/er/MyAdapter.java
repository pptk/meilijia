package com.weiwei.meilijia.er;

import java.util.List;

import com.example.yu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private List<ZixunModle> list;

	public MyAdapter(Context context, List<ZixunModle> list) {
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
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ZixunModle model = list.get(position);
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.info_item, null);
			Log.i("执行convertView", "if(语句里面的)");
		}
	//	ImageView touxiang = (ImageView) convertView.findViewById(R.id.touxiang);
		TextView username = (TextView) convertView.findViewById(R.id.username);
		TextView showtime = (TextView) convertView.findViewById(R.id.showtime);
		TextView content = (TextView) convertView.findViewById(R.id.content);
		TextView cid = (TextView) convertView.findViewById(R.id.showid);

		//对需要显示的选项进行处理
	/*	byte[] x = null;
		try {
			x = PageService.getImage(model.getTouxiang());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap image=BitmapFactory.decodeByteArray(x,
				0, x.length);//通过byte数组获取bitmap图片。
//		String neirong= model.getNr();
		Log.i("打印image", image.toString());
   */
//		赋值
	//	touxiang.setImageBitmap(image);
		username.setText(model.getName());//显示前100个字符
		showtime.setText(model.getTime());
		content.setText(model.getContent());
		cid.setText(model.getCid());//显示cid


		return convertView;
	}
}
