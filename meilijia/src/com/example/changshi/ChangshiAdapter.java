package com.example.changshi;

import java.util.List;

import com.example.meifushow.AsynctaskImageLoad;
import com.example.meifushow.CallbackImpl;
import com.example.yu.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangshiAdapter extends BaseAdapter{
    private List<ChangshiInfo> list;
    private Context context;
    private AsynctaskImageLoad loader=new AsynctaskImageLoad();
    public ChangshiAdapter(List<ChangshiInfo>list,Context context){
    	super();
    	this.list=list;
    	this.context=context;
    }
	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO �Զ����ɵķ������
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO �Զ����ɵķ������
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater iflater = LayoutInflater.from(context);
			convertView = iflater.inflate(R.layout.changshi_item, null);
		}
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView id=(TextView) convertView.findViewById(R.id.id);
		TextView introduce = (TextView) convertView.findViewById(R.id.introduce);
		ImageView priture = (ImageView) convertView.findViewById(R.id.picture);
		id.setText(list.get(position).getId());
		name.setText(list.get(position).getName());
		introduce.setText(list.get(position).getIntroduce());
	    loadImage("http://192.168.2.115:8080/meilijia3/"+list.get(position).getPicture(), priture);
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
