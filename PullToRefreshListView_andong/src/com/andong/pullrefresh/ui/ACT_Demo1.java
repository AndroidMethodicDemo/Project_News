package com.andong.pullrefresh.ui;

import java.util.ArrayList;
import java.util.List;

import com.andong.pullrefresh.R;
import com.andong.pullrefresh.R.id;
import com.andong.pullrefresh.R.layout;
import com.andong.pullrefresh.view.LoadDataCallback;
import com.andong.pullrefresh.view.RefreshListView;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ACT_Demo1 extends Activity {

	private List<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_demo1);
		
		final RefreshListView mListView = (RefreshListView) findViewById(R.id.refreshlistview);
		data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add("这是一条ListView的数据" + i);
		}
		
		final MyAdapter adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(new LoadDataCallback() {
			
			@Override
			public BaseAdapter loadNewData() {
			/*				
 * 			new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(2000);
						data.add(0, "这是刷新出来的数据");
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								adapter.notifyDataSetChanged();
								mListView.onRefreshFinish();
							}
						});
					}
				}).start();
				*/
				SystemClock.sleep(2000);
				data.add(0, "这是刷新出来的数据");
//				adapter.notifyDataSetChanged();
				return adapter;
			}
			
			@Override
			public BaseAdapter loadOldData() {
				/*new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(2000);
						data.add("这是加载更多出来的数据");
						data.add("这是加载更多出来的数据");
						data.add("这是加载更多出来的数据");
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								adapter.notifyDataSetChanged();
								mListView.onRefreshFinish();
							}
						});
					}
				}).start();*/
				SystemClock.sleep(2000);
				data.add("这是加载更多出来的数据");
				data.add("这是加载更多出来的数据");
				data.add("这是加载更多出来的数据");
				
				return adapter;
			}

		});
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = null;
			if(convertView == null) {
				tv = new TextView(ACT_Demo1.this);
			} else {
				tv = (TextView) convertView;
			}
			
			tv.setText(data.get(position));
			tv.setTextSize(18);
			return tv;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}
}
