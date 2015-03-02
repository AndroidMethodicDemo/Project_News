package com.andong.pullrefresh.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.andong.pullrefresh.R;

/**
 * 模拟一个详情页类
 * @author yajun
 *
 */
public class ACT_Detail extends ACT_Base{

	private TextView tvContent;
	private ListView listView;
	private String tag=ACT_Detail.class.getSimpleName();
	private ArrayList<String> data;
	
	@Override
	protected void initViews() {
		setContentView(R.layout.act_detail);
		tvContent=(TextView) findViewById(R.id.tvContent);
		listView=(ListView) findViewById(R.id.listView);
	}

	@Override
	protected void bindEvents() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.w(tag, "onItemClick-position："+position+",id："+id);
				Intent intent = new Intent(mContext, ACT_Detail.class);
				intent.putExtra("content", data.get((int) id));
				startActivity(intent);
			}

		});
	}

	@Override
	protected void initDatas() {
		Intent intent = getIntent();
		String content = intent.getStringExtra("content");
		tvContent.setText(content);
		//
		data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add("这是一条ListView的数据" + i);
		}
		listView.setAdapter(new MyAdapter(mContext,data));
	}

}
