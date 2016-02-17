package com.weiwei.meilijia.base;

import java.util.List;
import java.util.Map;

import com.example.yu.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassifyAreaListAdapter extends BaseAdapter {
	private final String TAG = "ClassifyAreaListAdapter";
	private Context context;
	private List<Map<String, String>> list;

	public ClassifyAreaListAdapter(Context context,
			List<Map<String, String>> list) {
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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "==getView()");
		ViewHolder mHolder = null;
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_classify_areas, parent, false);
			mHolder.textView_item_classifyAreas_name = (TextView) convertView
					.findViewById(R.id.textView_item_classifyAreas_name);
			mHolder.imageView_item_classify_areas = (ImageView) convertView
					.findViewById(R.id.imageView_item_classify_areas);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.textView_item_classifyAreas_name.setText(list.get(position)
				.get("areaName"));
		mHolder.textView_item_classifyAreas_name.setTag(list.get(position).get(
				"id"));
		if (position % 2 != 0) {
			mHolder.imageView_item_classify_areas
					.setBackgroundResource(R.color.gray);
		} else {
			mHolder.imageView_item_classify_areas
					.setBackgroundResource(R.color.white);
		}
		return convertView;
	}

	class ViewHolder {
		public TextView textView_item_classifyAreas_name;
		public ImageView imageView_item_classify_areas;
	}
}
