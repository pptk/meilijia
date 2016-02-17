package com.example.yu;

import java.util.ArrayList;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import com.weiwei.meilijia.yi.MainActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.weiwei.meilijia.adapter.IndexGalleryAdapter;
import com.weiwei.meilijia.adapter.IndexGalleryItemData;
import com.weiwei.meilijia.base.BaseActivity;
import com.weiwei.meilijia.base.BianhuaActivity;
import com.weiwei.meilijia.er.ShowListActivity;
import com.weiwei.meilijia.er.ZixunListActivity;
import com.weiwei.meilijia.second.FindtixinActivity;
import com.weiwei.meilijia.widgets.jazzviewpager.JazzyViewPager;
import com.weiwei.meilijia.widgets.jazzviewpager.JazzyViewPager.TransitionEffect;
import com.weiwei.meilijia.widgets.jazzviewpager.OutlineContainer;
import com.weiwei.meilijia.yi.Shoping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;


public class IndexActivity extends BaseActivity {
//public static final String TAG = IndexActivity.class.getSimpleName();
public ImageLoader imageLoader=ImageLoader.getInstance();
private ImageView mMiaoShaImage = null;
private TextView mIndexPrice = null;
private TextView mIndexRawPrice = null;

//=============中部导航栏模块=====
private ImageButton shake,lottery,recharge,groupbuy,history,order,promotion,collent;
private Intent mIntent,nIntent,iIntent,tIntent,oIntent,pIntent,yIntent;

// ============== 广告切换 ===================
private JazzyViewPager mViewPager = null;
/**
 * 装指引的ImageView数组
 */
private ImageView[] mIndicators;

/**
 * 装ViewPager中ImageView的数组
 */
private ImageView[] mImageViews;
private List<String> mImageUrls = new ArrayList<String>();
private LinearLayout mIndicator = null;
private String mImageUrl = null;
private static final int MSG_CHANGE_PHOTO = 1;
/** 图片自动切换时间 */
private static final int PHOTO_CHANGE_TIME = 3000;
// ============== 广告切换 ===================

private Gallery mStormGallery = null;
private Gallery mPromotionGallery = null;
private IndexGalleryAdapter mStormAdapter = null;
private IndexGalleryAdapter mPromotionAdapter = null;
private List<IndexGalleryItemData> mStormListData = new ArrayList<IndexGalleryItemData>();
private List<IndexGalleryItemData> mPromotionListData = new ArrayList<IndexGalleryItemData>();
private IndexGalleryItemData mItemData = null;

public String username;
//private LinearLayout mTopLayout = null;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_meiliquan);
	JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
    JPushInterface.init(this);     		// 初始化 JPush
	imageLoader.init(ImageLoaderConfiguration.createDefault(this));
	mHandler = new Handler(getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_CHANGE_PHOTO:
				int index = mViewPager.getCurrentItem();
				if (index == mImageUrls.size() - 1) {
					index = -1;
				}
				mViewPager.setCurrentItem(index + 1);
				mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO,
						PHOTO_CHANGE_TIME);
			}
		}

	};

	initData();

	findViewById();
	initView();
}

@SuppressWarnings("deprecation")
@Override
protected void findViewById() {
	// TODO Auto-generated method stub
	SharedPreferences sp=getSharedPreferences("actm", MODE_PRIVATE);
    username=sp.getString("uname", "");
	
	mIndexPrice = (TextView) findViewById(R.id.index_miaosha_price);
	mIndexRawPrice = (TextView) findViewById(R.id.index_miaosha_raw_price);

	mMiaoShaImage = (ImageView) findViewById(R.id.index_miaosha_image);
	mViewPager = (JazzyViewPager) findViewById(R.id.index_product_images_container);
	mIndicator = (LinearLayout) findViewById(R.id.index_product_images_indicator);

	mStormGallery = (Gallery) findViewById(R.id.index_jingqiu_gallery);
	mPromotionGallery = (Gallery) findViewById(R.id.index_tehui_gallery);
	shake=(ImageButton)findViewById(R.id.index_shake);
	groupbuy=(ImageButton)findViewById(R.id.index_groupbuy_btn);
	lottery=(ImageButton)findViewById(R.id.index_lottery_btn);
	recharge=(ImageButton)findViewById(R.id.index_recharge_btn);
	history=(ImageButton)findViewById(R.id.index_history_btn);
	order=(ImageButton)findViewById(R.id.index_order_btn);
	promotion=(ImageButton)findViewById(R.id.index_promotion_btn);
	collent=(ImageButton)findViewById(R.id.index_collect_btn);
	
	//添加事件
	shake.setOnClickListener(indexClickListener);
	lottery.setOnClickListener(indexClickListener);
	recharge.setOnClickListener(indexClickListener);
	groupbuy.setOnClickListener(indexClickListener);
	history.setOnClickListener(indexClickListener);
	order.setOnClickListener(indexClickListener);
	promotion.setOnClickListener(indexClickListener);
	collent.setOnClickListener(indexClickListener);
}


private OnClickListener indexClickListener=new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.index_shake:
			mIntent=new Intent(IndexActivity.this, MainActivity.class);
			startActivity(mIntent);
			break;
		case R.id.index_lottery_btn:
			nIntent=new Intent(IndexActivity.this, BianhuaActivity.class);
			startActivity(nIntent);
			break;
		case R.id.index_recharge_btn:
			mIntent=new Intent(IndexActivity.this, CalendarActivity.class);
			startActivity(mIntent);
			break;
		case R.id.index_groupbuy_btn:
			iIntent =new Intent(IndexActivity.this,FindtixinActivity.class);
			startActivity(iIntent);
			break;
		case R.id.index_history_btn:
			tIntent =new Intent(IndexActivity.this,Shoping.class);
			startActivity(tIntent);
			break;
		case R.id.index_order_btn:
			oIntent =new Intent(IndexActivity.this,ZixunListActivity.class);
			oIntent.putExtra("username",username);
			startActivity(oIntent);
			break;
		case R.id.index_promotion_btn:
		    pIntent=new Intent(IndexActivity.this,TestfaceActivity.class);
		    startActivity(pIntent);
		    break;	
		case R.id.index_collect_btn:
			yIntent=new Intent(IndexActivity.this,ShowListActivity.class);
			startActivity(yIntent);
			break;
		default:
			break;
		}
		
	}
};

@Override
protected void initView() {
	
	
	
	
	// TODO Auto-generated method stub
	ImageLoader.getInstance().displayImage(
			"drawable://" + R.drawable.miaosha, mMiaoShaImage);
	mIndexPrice.setText("80.8%");
	mIndexRawPrice.setText("￥60.9%");
	mIndexRawPrice.getPaint().setFlags(
			Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

	// ======= 初始化ViewPager ========
	mIndicators = new ImageView[mImageUrls.size()];
	if (mImageUrls.size() <= 1) {
		mIndicator.setVisibility(View.GONE);
	}

	for (int i = 0; i < mIndicators.length; i++) {
		ImageView imageView = new ImageView(this);
		LayoutParams params = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT, 1.0f);
		if (i != 0) {
			params.leftMargin = 5;
		}
		imageView.setLayoutParams(params);
		mIndicators[i] = imageView;
		if (i == 0) {
			mIndicators[i]
					.setBackgroundResource(R.drawable.android_activities_cur);
		} else {
			mIndicators[i]
					.setBackgroundResource(R.drawable.android_activities_bg);
		}

		mIndicator.addView(imageView);
	}

	mImageViews = new ImageView[mImageUrls.size()];

	for (int i = 0; i < mImageViews.length; i++) {
		ImageView imageView = new ImageView(this);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		mImageViews[i] = imageView;
	}
	mViewPager.setTransitionEffect(TransitionEffect.CubeOut);
	mViewPager.setCurrentItem(0);
	mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);

	mViewPager.setAdapter(new MyAdapter());
	mViewPager.setOnPageChangeListener(new MyPageChangeListener());
	mViewPager.setOnTouchListener(new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (mImageUrls.size() == 0 || mImageUrls.size() == 1)
				return true;
			else
				return false;
		}
	});
	
	// ======= 初始化ViewPager ========

	mStormAdapter = new IndexGalleryAdapter(this,
			R.layout.activity_index_gallery_item, mStormListData,
			new int[] { R.id.index_gallery_item_image,
					R.id.index_gallery_item_text });

	mStormGallery.setAdapter(mStormAdapter);

	mPromotionAdapter = new IndexGalleryAdapter(this,
			R.layout.activity_index_gallery_item, mPromotionListData,
			new int[] { R.id.index_gallery_item_image,
					R.id.index_gallery_item_text });

	mPromotionGallery.setAdapter(mPromotionAdapter);

	mStormGallery.setSelection(3);
	mPromotionGallery.setSelection(3);
	
}

private void initData() {
	mImageUrl = "drawable://" + R.drawable.ad_default1;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.ad_default2;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.ad_default3;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.ad_default4;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.image1;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.image5;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.image6;
	mImageUrls.add(mImageUrl);

	mImageUrl = "drawable://" + R.drawable.image7;
	mImageUrls.add(mImageUrl);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(1);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_01);
	mItemData.setPrice("￥79.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(2);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_02);
	mItemData.setPrice("￥89.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(3);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_03);
	mItemData.setPrice("￥99.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(4);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_04);
	mItemData.setPrice("￥109.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(5);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_05);
	mItemData.setPrice("￥119.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(6);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_06);
	mItemData.setPrice("￥129.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(7);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_07);
	mItemData.setPrice("￥139.00");
	mStormListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(8);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_08);
	mItemData.setPrice("￥69.00");
	mPromotionListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(9);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_09);
	mItemData.setPrice("￥99.00");
	mPromotionListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(10);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_10);
	mItemData.setPrice("￥109.00");
	mPromotionListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(11);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_11);
	mItemData.setPrice("￥119.00");
	mPromotionListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(12);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_12);
	mItemData.setPrice("￥129.00");
	mPromotionListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(13);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_13);
	mItemData.setPrice("￥139.00");
	mPromotionListData.add(mItemData);

	mItemData = new IndexGalleryItemData();
	mItemData.setId(14);
	mItemData.setImageUrl("drawable://" + R.drawable.index_gallery_14);
	mItemData.setPrice("￥149.00");
	mPromotionListData.add(mItemData);
}


public class MyAdapter extends PagerAdapter {

	@Override
	public int getCount() {
		return mImageViews.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		if (view instanceof OutlineContainer) {
			return ((OutlineContainer) view).getChildAt(0) == obj;
		} else {
			return view == obj;
		}
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(mViewPager
				.findViewFromObject(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		ImageLoader.getInstance().displayImage(mImageUrls.get(position),
				mImageViews[position]);
		((ViewPager) container).addView(mImageViews[position], 0);
		mViewPager.setObjectForPosition(mImageViews[position], position);
		return mImageViews[position];
	}

}

/**
 * 当ViewPager中页面的状态发生改变时调用
 * 
 * @author Administrator
 * 
 */
private class MyPageChangeListener implements OnPageChangeListener {

	/**
	 * This method will be invoked when a new page becomes selected.
	 * position: Position index of the new selected page.
	 */
	public void onPageSelected(int position) {
		setImageBackground(position);
	}

	public void onPageScrollStateChanged(int arg0) {

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
}

/**
 * 设置选中的tip的背景
 * 
 * @param selectItemsIndex
 */
private void setImageBackground(int selectItemsIndex) {
	for (int i = 0; i < mIndicators.length; i++) {
		if (i == selectItemsIndex) {
			mIndicators[i]
					.setBackgroundResource(R.drawable.android_activities_cur);
		} else {
			mIndicators[i]
					.setBackgroundResource(R.drawable.android_activities_bg);
		}
	}
}
}

