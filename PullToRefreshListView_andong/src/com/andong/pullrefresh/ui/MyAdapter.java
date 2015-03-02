package com.andong.pullrefresh.ui;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<String> data;
	private String tag=MyAdapter.class.getSimpleName();
	private Context mContext;
	
	public MyAdapter(Context context,ArrayList<String> data) {
		mContext=context;
		this.data=data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		Log.w(tag, "getView-position："+position);
		TextView tv = null;
		if(convertView == null) {
			tv = new TextView(mContext);
		} else {
			tv = (TextView) convertView;
		}
		
		tv.setText(data.get(position));
		tv.setTextSize(18);
		return tv;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		Log.w(tag, "getItemId-position："+position);
		return 0;
	}
}	
