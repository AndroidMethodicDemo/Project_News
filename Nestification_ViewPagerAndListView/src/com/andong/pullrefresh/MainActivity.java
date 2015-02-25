package com.andong.pullrefresh;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<String> data;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final RefreshListView mListView = (RefreshListView) findViewById(R.id.refreshlistview);
		data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add("这是一条ListView的数据" + i);
		}
		// 给ListView添加ViewPager头
//		viewPager = (ViewPager) View.inflate(this, R.layout.viewpager, null);
//		viewPager.setAdapter(new MyPagerAdapter());
//		mListView.addHeaderView(viewPager);
		
		// 填充数据
		final MyAdapter adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(new LoadDataCallback() {

			@Override
			public BaseAdapter loadNewData() {
				/*
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() { SystemClock.sleep(2000);
				 * data.add(0, "这是刷新出来的数据"); runOnUiThread(new Runnable() {
				 * 
				 * @Override public void run() { adapter.notifyDataSetChanged();
				 * mListView.onRefreshFinish(); } }); } }).start();
				 */
				SystemClock.sleep(2000);
				data.add(0, "这是刷新出来的数据");
				// adapter.notifyDataSetChanged();
				return adapter;
			}

			@Override
			public BaseAdapter loadOldData() {
				/*
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() { SystemClock.sleep(2000);
				 * data.add("这是加载更多出来的数据"); data.add("这是加载更多出来的数据");
				 * data.add("这是加载更多出来的数据"); runOnUiThread(new Runnable() {
				 * 
				 * @Override public void run() { adapter.notifyDataSetChanged();
				 * mListView.onRefreshFinish(); } }); } }).start();
				 */
				SystemClock.sleep(2000);
				data.add("这是加载更多出来的数据");
				data.add("这是加载更多出来的数据");
				data.add("这是加载更多出来的数据");

				return adapter;
			}

		});

	}
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * 返回true代表复用view对象
		 * 官方建议写法 return view == obj;
		 */
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		/**
		 * 销毁多余的item
		 * position就是将要被销毁的item的索引
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			mViewPager.removeView(imageViewList.get(position % imageViewList.size()));
		}

		/**
		 * 当需要加载item时出发此方法
		 * position 就是将要被加载的item的索引
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
//			int currentItem = mViewPager.getCurrentItem();
//			mViewPager.addView(imageViewList.get(position % imageViewList.size()));
//			return imageViewList.get(position % imageViewList.size());
			return null;
		}
		
	}
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = null;
			if (convertView == null) {
				tv = new TextView(MainActivity.this);
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
