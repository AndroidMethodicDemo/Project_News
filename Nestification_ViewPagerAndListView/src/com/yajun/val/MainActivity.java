package com.yajun.val;

import java.util.ArrayList;
import java.util.List;

import com.andong.pullrefresh.R;
import com.yajun.val.view.AdViewPagerHasBug;
import com.yajun.val.view.LoadDataCallback;
import com.yajun.val.view.RefreshListView;

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
/**
 * 当ListVIew不设置Adapter时，addHeader不起作用（Adapter的size可以为0）
 * @author yajun
 *
 */
public class MainActivity extends Activity {

	private List<String> data;
	private AdViewPagerHasBug adViewPager;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final RefreshListView mListView = (RefreshListView) findViewById(R.id.refreshlistview);
		data = new ArrayList<String>();
		/*for (int i = 0; i < 30; i++) {
			data.add("这是一条ListView的数据" + i);
		}*/
		// 给ListView添加ViewPager头
		adViewPager=new AdViewPagerHasBug(this);
		
		mListView.addHeaderView(adViewPager);
		// 填充数据
		final MyAdapter adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(new LoadDataCallback() {

			@Override
			public BaseAdapter loadNewData() {
				
				SystemClock.sleep(2000);
				data.add(0, "这是刷新出来的数据");
				// adapter.notifyDataSetChanged();
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
		
		ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();
		// 初始化图片和描述信息等.
		int[] imageResIDs = { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e };
		for (int i = 0; i < imageResIDs.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);
		}
		String[] imageDescriptionArrays = { "巩俐不低俗，我就不能低俗",
				"扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" };
		adViewPager.setData(imageViewList, imageDescriptionArrays);

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
