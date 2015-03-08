package com.yajun.val.view;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.andong.pullrefresh.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.yajun.val.DensityUtil;

public class AdViewPager extends RelativeLayout {
	
	private ViewPager viewPager;
	private ImageView vpDefault;
	private LinearLayout vgPoint;
	// ----------
	private Context context;
	private BitmapUtils bitmapUtils;
	private BitmapDisplayConfig bigPicDisplayConfig;
	private MyPagerAdapter pagerAdapter;
	private List<ImageView> pointList;
	private View view;
	
	public AdViewPager(Context context) {
		super(context);
		this.context = context;
		initBitmapUtils();
		view = LayoutInflater.from(context).inflate(
				R.layout.point_viewpager, this, true);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
//		tvImageDescription = (TextView) root
//				.findViewById(R.id.tv_image_descriptioin);
		vgPoint = (LinearLayout) view.findViewById(R.id.ll_point_group);
		
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		view.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*
                 *  dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
                 *  将事件交给viewPager，避免一些问题
                 */
                return viewPager.dispatchTouchEvent(event);
            }
        });
	}


	private void initBitmapUtils() {
		bitmapUtils = new BitmapUtils(context);
		// 压缩图片
		bigPicDisplayConfig = new BitmapDisplayConfig();
		bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
		bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils
				.getScreenSize(context));
	}

	public void setData(String[] picUrls) {
		/*
		 * 给ViewPager设置数据
		 */
		if (picUrls == null) {
			vpDefault.setVisibility(View.VISIBLE);
		} else if (picUrls.length == 0) {
			vpDefault.setVisibility(View.VISIBLE);
		} else {
			/*
			 *  to cache all page, or we will see the right item delayed
			 *  缓存所有的页面，避免右边条目的延迟
			 */
			viewPager.setOffscreenPageLimit(2);
			pagerAdapter = new MyPagerAdapter(picUrls);
			viewPager.setAdapter(pagerAdapter);
			//viewPager.setCurrentItem(picUrls.size(), false);// 这句话应该放在setAdapter前还是setAdapter后？
		}
		/*
		 * 给ViewPager下面的指示点设置数据
		 */
		pointList = new LinkedList<ImageView>();
		for (int i = 0; i < picUrls.length; i++) {
			ImageView imageView = new ImageView(context);
			android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					DensityUtil.dip2px(context, 15), DensityUtil.dip2px(
							context, 15));
			int sixdp = DensityUtil.dip2px(context, 3);
			imageView.setPadding(sixdp, sixdp, sixdp, sixdp);
			//
			if (i == 0) {
				imageView.setImageResource(R.drawable.point_enable);
			} else {
				imageView.setImageResource(R.drawable.point_unenable);
			}
			vgPoint.addView(imageView, layoutParams);
			// ------------
			pointList.add(imageView);
		}

	}

	/**
	 * PagerAdapter
	 */
	class MyPagerAdapter extends PagerAdapter {
		private String[] picUrls;

		public MyPagerAdapter(String[] picUrls) {
			super();
			this.picUrls = picUrls;
		}

		public void setPicUrls(String[] picUrls) {
			this.picUrls = picUrls;
		}

		// 得到adapter的个数
		@Override
		public int getCount() {
			//return Integer.MAX_VALUE;
			return picUrls.length;
		}

		// 是否把对象作为view
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == (View) obj;
		}

		// 初始化一个item
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = new ImageView(context);

			int size = picUrls.length;
			int location = position % size;
			bitmapUtils.display(iv, picUrls[location], bigPicDisplayConfig,
					null);

			((ViewPager) container).addView(iv);
			return iv;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

	}// End of MyPagerAdapter

	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {

		}

		private void reset() {

			for (ImageView imageView : pointList) {
				imageView.setImageResource(R.drawable.point_unenable);
			}

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			if ( view!= null) {
                view.invalidate();
            }
		}

		@Override
		public void onPageSelected(int position) {
			if (pointList != null) {
				int location = position % pointList.size();
				reset();
				pointList.get(location).setImageResource(
						R.drawable.point_enable);
			}
		}

	}

}
