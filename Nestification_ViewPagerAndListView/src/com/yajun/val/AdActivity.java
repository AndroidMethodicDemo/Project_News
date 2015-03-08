package com.yajun.val;

import java.util.ArrayList;

import com.andong.pullrefresh.R;
import com.yajun.val.view.AdViewPager;
import com.yajun.val.view.AdViewPagerHasBug;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class AdActivity extends Activity {

	private AdViewPager adViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adViewPager = new AdViewPager(this);
		init2();
		setContentView(adViewPager);
	}
	
	/**
	 * 
	 */
	private void init1(){
		
		ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();
		// 初始化图片和描述信息等.
		int[] imageResIDs = { R.drawable.a, R.drawable.b, R.drawable.c
			};
		for (int i = 0; i < imageResIDs.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);
		}
		String[] imageDescriptionArrays = { "巩俐不低俗，我就不能低俗",
				"扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级" };
//		adViewPager.setData(imageViewList, imageDescriptionArrays);
	}
	
	/**
	 * 
	 */
	private void init2(){
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				String[] imageDescriptionArrays = { "巩俐不低俗，我就不能低俗",
						"扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" };
//				String[] imageUrls = { "http://img2.imgtn.bdimg.com/it/u=2777751966,45838676&fm=21&gp=0.jpg",
//						"http://t-1.tuzhan.com/2e6d3a949a30/c-1/l/2012/09/21/00/7e4608d4b2da4c65b672abae91aed8e9.png", "http://img5.imgtn.bdimg.com/it/u=2818577223,4010905518&fm=21&gp=0.jpg",
//						"http://d.hiphotos.baidu.com/zhidao/pic/item/42a98226cffc1e177988b20f4990f603738de9dd.jpg", "http://www.shanxigov.cn/n16/n8319541/n8319672/n8325142/n8351498.files/n9012982.jpg" };
				
				String[] imageUrls ={"http://www.jizhigame.com/./Res/badminton_min.jpg", "http://www.jizhigame.com/./Res/basketball_min.jpg"," http://www.jizhigame.com/./Res/football_min.jpg"," http://www.jizhigame.com/./Res/football_min.jpg"," http://www.jizhigame.com/./Res/football_min.jpg"};
;
				adViewPager.setData(imageUrls);
			}
		}.execute();
	}
	
}
