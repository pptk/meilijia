package com.weiwei.meilijia.er;

import java.util.List;

import com.example.yu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SreplayAdapter extends BaseAdapter{
   
	private List<SreplayModle> list;
	private Context context;


	public SreplayAdapter(Context context, List<SreplayModle> list) {
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
			convertView = iflater.inflate(R.layout.sinfo_item, null);
		}
		TextView username = (TextView) convertView.findViewById(R.id.username);
		TextView time = (TextView) convertView
				.findViewById(R.id.time);
		TextView content = (TextView) convertView.findViewById(R.id.content);


		username.setText(list.get(position).getSrname());
		time.setText(list.get(position).getSrtime());
		content.setText(list.get(position).getSrcontent());
		return convertView;
	}


}
