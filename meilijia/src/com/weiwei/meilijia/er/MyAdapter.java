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
			Log.i("ִ��convertView", "if(��������)");
		}
	//	ImageView touxiang = (ImageView) convertView.findViewById(R.id.touxiang);
		TextView username = (TextView) convertView.findViewById(R.id.username);
		TextView showtime = (TextView) convertView.findViewById(R.id.showtime);
		TextView content = (TextView) convertView.findViewById(R.id.content);
		TextView cid = (TextView) convertView.findViewById(R.id.showid);

		//����Ҫ��ʾ��ѡ����д���
	/*	byte[] x = null;
		try {
			x = PageService.getImage(model.getTouxiang());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap image=BitmapFactory.decodeByteArray(x,
				0, x.length);//ͨ��byte�����ȡbitmapͼƬ��
//		String neirong= model.getNr();
		Log.i("��ӡimage", image.toString());
   */
//		��ֵ
	//	touxiang.setImageBitmap(image);
		username.setText(model.getName());//��ʾǰ100���ַ�
		showtime.setText(model.getTime());
		content.setText(model.getContent());
		cid.setText(model.getCid());//��ʾcid


		return convertView;
	}
}
