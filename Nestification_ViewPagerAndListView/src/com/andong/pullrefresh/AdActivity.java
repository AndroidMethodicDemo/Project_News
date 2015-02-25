package com.andong.pullrefresh;

import java.util.ArrayList;

import com.andong.pullrefresh.view.AdViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class AdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AdViewPager adViewPager = new AdViewPager(this);
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
		
		setContentView(adViewPager);
	}
}
