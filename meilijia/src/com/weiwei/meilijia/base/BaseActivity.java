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
	 * �󶨿ؼ�id
	 */
    protected abstract void findViewById();
    /**
	 * ��ʼ���ؼ�
	 */
	protected abstract void initView();
	/**
	 * ͨ����������Activity
	 * 
	 * @param pClass
	 * @param object 
	 */
	protected void openActivity(Class<?> pClass){
		openActivity(pClass,null);
	}
	/**
	 * ͨ����������Activity�����Һ���Bundle����
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
	 * ͨ��Action����Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * ͨ��Action����Activity�����Һ���Bundle����
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
	
	/**���ؽ�����*/
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
		progressDialog.setMessage("���Ժ�����Ŭ�����ء���");
		progressDialog.show();
	}
	//��ʾ
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
		
		//��õ�ǰ����汾��Ϣ
		protected String getVersionName() throws Exception {
			// ��ȡpackagemanager��ʵ��
			PackageManager packageManager = getPackageManager();
			// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			return packInfo.versionName;
		}

		
	//�@���O����Ϣ
		protected String getDeviceId() throws Exception {
			String deviceId=tManager.getDeviceId();
			
			return deviceId;
			
		}
		
		/**
		 * ��ȡSIM�����к�
		 * 
		 * @return
		 */
		protected String getToken() {
			return tManager.getSimSerialNumber();
		}

		/*�@��ϵ�y�汾*/
		
		protected String getClientOs() {
			return android.os.Build.ID;
		}
		
		/*�@��ϵ�y�汾̖*/
		protected String getClientOsVer() {
			return android.os.Build.VERSION.RELEASE;
		}
		
		//�@��ϵ�y�Z�԰�
		protected String getLanguage() {
			return Locale.getDefault().getLanguage();
		}
		
	    protected String getCountry() {
			
			return Locale.getDefault().getCountry();
		}

	
}
