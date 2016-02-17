package com.weiwei.meilijia.base;

import java.util.Locale;

import com.example.yu.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public abstract class BaseActivity extends Activity{
    public static final String TAG =BaseActivity.class.getSimpleName();
    protected Handler mHandler=null;
    protected InputMethodManager imm;
    private TelephonyManager tManager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    }
    @Override
    protected void onPause(){
    	super.onPause();
    }
    protected void onRestart(){
    	super.onRestart();
    }
    @Override
    protected void onResume(){
    	super.onResume();
    }
    @Override
    protected void onStart(){
    	super.onStart();
    }
    @Override
    protected void onStop(){
    	super.onStop();
    }
    /**
	 * 绑定控件id
	 */
    protected abstract void findViewById();
    /**
	 * 初始化控件
	 */
	protected abstract void initView();
	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 * @param object 
	 */
	protected void openActivity(Class<?> pClass){
		openActivity(pClass,null);
	}
	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
	/**
	 * 通过Action启动Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * 通过Action启动Activity，并且含有Bundle数据
	 * 
	 * @param pAction
	 * @param pBundle
	 */
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
	
	/**加载进度条*/
	public void showProgressDialog() {
		ProgressDialog progressDialog = null;
		
		if(progressDialog!=null){
			progressDialog.cancel();
		}
		progressDialog=new ProgressDialog(this);
		Drawable drawable=getResources().getDrawable(R.drawable.loading_animation);
		progressDialog.setIndeterminateDrawable(drawable);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setMessage("请稍候，正在努力加载。。");
		progressDialog.show();
	}
	//提示
		protected void DisPlay(String content){
			Toast.makeText(this, content, 1).show();
		}
		public void DisplayToast(String str) {
			Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		}
		
		protected void hideOrShowSoftInput(boolean isShowSoft,EditText editText) {
			if (isShowSoft) {
				imm.showSoftInput(editText, 0);
			}else {
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			}
		}
		
		//获得当前程序版本信息
		protected String getVersionName() throws Exception {
			// 获取packagemanager的实例
			PackageManager packageManager = getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			return packInfo.versionName;
		}

		
	//獲得設備信息
		protected String getDeviceId() throws Exception {
			String deviceId=tManager.getDeviceId();
			
			return deviceId;
			
		}
		
		/**
		 * 获取SIM卡序列号
		 * 
		 * @return
		 */
		protected String getToken() {
			return tManager.getSimSerialNumber();
		}

		/*獲得系統版本*/
		
		protected String getClientOs() {
			return android.os.Build.ID;
		}
		
		/*獲得系統版本號*/
		protected String getClientOsVer() {
			return android.os.Build.VERSION.RELEASE;
		}
		
		//獲得系統語言包
		protected String getLanguage() {
			return Locale.getDefault().getLanguage();
		}
		
	    protected String getCountry() {
			
			return Locale.getDefault().getCountry();
		}

	
}
