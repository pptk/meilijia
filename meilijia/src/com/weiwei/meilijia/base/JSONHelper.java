package com.weiwei.meilijia.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONHelper {
	private final static String TAG = "JSONHelper";

	public static Map<String, String> parserJsonAll(String jsonString,
			String key) {
		Log.i(TAG, "==jsonStringToMap");
		JSONObject jsonObject = null;
		Map<String, String> map = new HashMap<String, String>();
		Log.i(TAG, "jsonString==" + jsonString);
		try {
			jsonObject = new JSONObject(jsonString);
			Log.i(TAG, "jsonObject.toString()==" + jsonObject.toString());
			if (key != null) {
				jsonObject = jsonObject.getJSONObject(key);
			}
			Log.i(TAG, "jsonObject.toString()==" + jsonObject.toString());
			Iterator<?> it = jsonObject.keys();
			while (it.hasNext()) {// 遍历JSONObject
				String keyString = (String) it.next().toString();
				String valueString = jsonObject.getString(keyString);
				// Log.i(TAG, "测试keyString==" + keyString);
				// Log.i(TAG, "测试valueString==" + valueString);
				map.put(keyString, valueString);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "map==" + map);
		return map;
	}

	public static Map<String, String> parserJson(String jsonString,
			String[] keyNames, String key) {
		Log.i(TAG, "==jsonStringToMap");
		JSONObject jsonObject = null;
		Map<String, String> map = new HashMap<String, String>();

		try {
			jsonObject = new JSONObject(jsonString);
			Log.i(TAG, "jsonObject.toString()==" + jsonObject.toString());
			if (key != null) {
				jsonObject = jsonObject.getJSONObject(key);
			}
			Log.i(TAG, "jsonObject.toString()==" + jsonObject.toString());
			for (int i = 0; i < keyNames.length; i++) {
				map.put(keyNames[i], jsonObject.getString(keyNames[i]));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "map==" + map);
		return map;
	}
}
