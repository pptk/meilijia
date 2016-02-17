package com.weiwei.meilijia.text;

import java.util.ArrayList;
import java.util.List;

import com.example.yu.R;
import com.weiwei.meilijia.model.Tb_shuiyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class MyAdapter extends BaseAdapter{
	public Context context;
	private List<Tb_shuiyou> list;
	private TextView tv1,tv2,tv3;
	
	public MyAdapter(Context context,List<Tb_shuiyou> list) {
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Tb_shuiyou model = list.get(position); 
		ViewGroup group = (ViewGroup) inflater.inflate(R.layout.test_record_item, null);
		tv1 = (TextView)group.findViewById(R.id.shui);
		tv2 = (TextView)group.findViewById(R.id.you);
		tv3 = (TextView)group.findViewById(R.id.time);
		tv1.setText(model.getShui());
		tv2.setText(model.getYou());
		tv3.setText(model.getTimedd());
		return group;
	}

}
