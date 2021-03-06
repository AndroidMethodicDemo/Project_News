package com.andong.pullrefresh.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andong.pullrefresh.R;
import com.andong.pullrefresh.view.LoadDataCallback;
import com.andong.pullrefresh.view.RefreshListView;
/**
 * 1. 演示RefreshListView1的用法
 * 2. Adapter中的getItemId与OnItemClick中的id相对应，OnItemClick中的position是ListView包含Header之后的索引
 * 		因此，在OnItemClick中用id比用position要好。
 * @author yajun
 *
 */
public class ACT_Demo1 extends ACT_Base {

	private RefreshListView mListView;
	private ArrayList<String> data;
	private String tag=ACT_Demo1.class.getSimpleName();

	@Override
	protected void initViews() {
		setContentView(R.layout.act_demo1);;
		mListView=(RefreshListView) findViewById(R.id.refreshlistview);
	}

	@Override
	protected void bindEvents() {
		mListView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.w(tag, "position："+position+",id："+id);
				Intent intent = new Intent(mContext, ACT_Detail.class);
				intent.putExtra("content", data.get((int) id));
				startActivity(intent);
			}

		});
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

				SystemClock.sleep(2000);
				data.add(0, "这是刷新出来的数据");
				return adapter;
			}
			
			@Override
			public BaseAdapter loadOldData() {

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
			Log.w(tag, "getView-position："+position);
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
			return position;
		}
	}	

}
