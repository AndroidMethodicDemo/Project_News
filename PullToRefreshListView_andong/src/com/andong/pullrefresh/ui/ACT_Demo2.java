package com.andong.pullrefresh.ui;

import java.util.ArrayList;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andong.pullrefresh.R;
import com.andong.pullrefresh.view.LoadDataCallback;
import com.andong.pullrefresh.view.RefreshListView1;
/**
 * 演示RefreshListView1的用法
 * @author yajun
 *
 */
public class ACT_Demo2 extends ACT_Base {
	
	private RefreshListView1 mListView;
	private ArrayList<String> data;

	@Override
	protected void initViews() {
		setContentView(R.layout.act_demo2);;
		mListView=(RefreshListView1) findViewById(R.id.refreshlistview);
	}

	@Override
	protected void bindEvents() {

	}

	@Override
	protected void initDatas() {
		data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add("这是一条ListView的数据" + i);
		}
		
		final MyAdapter adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(new LoadDataCallback() {
			
			@Override
			public BaseAdapter loadNewData() {
					
  			new Thread(new Runnable() {
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
				

				return adapter;
			}
			
			@Override
			public BaseAdapter loadOldData() {
				new Thread(new Runnable() {
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
				}).start();

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
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}
}
