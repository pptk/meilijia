package com.weiwei.meilijia.text;

import java.util.List;

import com.example.yu.R;
import com.weiwei.meilijia.dao.ShuiYouDao;
import com.weiwei.meilijia.model.Tb_shuiyou;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class RecordActivity extends Activity implements OnClickListener{
    private ImageView back;
    private ListView lv;
    private String TAG;
    private List<Tb_shuiyou> list;
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.test_record_activity);
    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		initView();
    }
    private void initView(){
    	back=(ImageView)findViewById(R.id.back);
    	back.setOnClickListener(this);	
    	lv = (ListView)findViewById(R.id.lv);
		
		ShuiYouDao dao = new ShuiYouDao(RecordActivity.this);// ����ShuiYouDao����
		// ��ȡ����������Ϣ�����洢��List���ͼ�����
		List<Tb_shuiyou> list = dao.findall();

		lv.setAdapter(new MyAdapter(this, list));// ΪListView�б���������Դ
    	
    	
    }
	public void onClick(View v) {
			// TODO Auto-generated method stub
		switch (v.getId()) {
		//返回
		case R.id.back:
			onBackPressed();
			break;
		//水油测试记录
		}
	}
	
}
