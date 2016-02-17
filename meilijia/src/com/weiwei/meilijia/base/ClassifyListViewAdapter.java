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



public class ClassifyListViewAdapter extends BaseAdapter {
	private final String TAG = "ClassifyListViewAdapter";
	private Context context;
	private List<Map<String, String>> list;

	public ClassifyListViewAdapter(Context context,
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
					R.layout.item_classify_provinces, parent, false);
			mHolder.textView_item_classify_name = (TextView) convertView
					.findViewById(R.id.textView_item_classify_name);
			mHolder.textView_item_classify_id = (TextView) convertView
					.findViewById(R.id.textView_item_classify_id);
			mHolder.imageView_item_classify_provinces = (ImageView) convertView
					.findViewById(R.id.imageView_item_classify_provinces);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.textView_item_classify_name.setText(list.get(position).get(
				"provinceName"));
		mHolder.textView_item_classify_id.setText(list.get(position).get("id"));

		return convertView;
	}

	class ViewHolder {
		public TextView textView_item_classify_name;
		public TextView textView_item_classify_id;
		public ImageView imageView_item_classify_provinces;
	}
}
