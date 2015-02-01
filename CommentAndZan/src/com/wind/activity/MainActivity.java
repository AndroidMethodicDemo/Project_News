package com.wind.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.wind.activity.R;
import com.wind.activity.listviewadapter.CommentAdapter;

public class MainActivity extends Activity {

	/**
	 * 每层楼显示数据的集合
	 */
	private List<Map<String, Object>> str = null;
	/**
	 * 每层楼的背景色，引数组的大小必须与str集合的大小相同
	 */
	private int[] color = new int[] { Color.CYAN, Color.RED, Color.BLUE };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去应用的标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) this.findViewById(R.id.main_listView);
		str = new ArrayList<Map<String, Object>>();
		/*
		 * 模拟显示的数据,最后一个用户评论的内容要与其他用户的评论内容分离开
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", new String[] { "这里显示用户名1", "这里显示用户名1", "这里显示用户名1" });
		map.put("comment",
				new String[] { "这里显示用户评论1", "这里显示用户评论1", "这里显示用户评论1" });
		map.put("lastComment", "这里显示最后一个用户评论！");
		str.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", new String[] { "这里显示用户名2", "这里显示用户名2", "这里显示用户名2" });
		map1.put("comment", new String[] { "这里显示用户评论2", "这里显示用户评论2",
				"这里显示用户评论2" });
		map1.put("lastComment", "这里显示最后一个用户评论！");
		str.add(map1);
		listView.setAdapter(new CommentAdapter(this, str, color));
	}
}