package com.andong.pullrefresh.view;

import java.util.ArrayList;
import java.util.List;

import com.andong.pullrefresh.R;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义组合控件
 * 
 * @author yajun
 * 
 */
public class AdViewPager extends RelativeLayout {
	private static final String TAG = AdViewPager.class.getSimpleName();
	private Context context;
	private List<ImageView> imageViewList;
	private ViewPager mViewPager;
	private TextView tvImageDescription;
	private LinearLayout llPointGroup;
	private String[] imageDescriptionArrays;

	private int previousPosition = 0; // 前一个被选中页面的position

	private boolean isStop = false;

	public AdViewPager(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public AdViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	private void initView(){
		View root = LayoutInflater.from(context).inflate(
				R.layout.point_viewpager, this, true);
		mViewPager = (ViewPager) root.findViewById(R.id.viewpager);
		tvImageDescription = (TextView) root
				.findViewById(R.id.tv_image_descriptioin);
		llPointGroup = (LinearLayout) root.findViewById(R.id.ll_point_group);
		
	}
	/**
	 * 调用此方法，将图片设置到ViewPager并添加圆点
	 */
	public void setData(ArrayList<ImageView> imageViewList,
			String[] imageDescriptionArrays) {
		this.imageViewList=imageViewList;
		this.imageDescriptionArrays=imageDescriptionArrays;
		// 根据图片个数，添加圆点
		View pointView;
		for (int i = 0; i < imageViewList.size(); i++) {
			// 每次循环添加一个点
			pointView = new View(getContext());
			pointView.setBackgroundResource(R.drawable.point_background);
			LayoutParams params = new LayoutParams(5, 5);
			params.leftMargin = 5;
			pointView.setLayoutParams(params);
			llPointGroup.addView(pointView);
		}

		// 把适配器和viewpager控件关联起来.
		mViewPager.setAdapter(new MyPagerAdapter());
		mViewPager.setOnPageChangeListener(new PointListener());

		// 初始化图片描述信息和点的选中状态
		tvImageDescription.setText(imageDescriptionArrays[0]);
		llPointGroup.getChildAt(0).setEnabled(false);

		// 设置初始被选中的item是 2147483647 / 2 % 5 = 3;
		// int currentItem = Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) %
		// imageViewList.size());
		int currentItem = Integer.MAX_VALUE / 2 - 3;
		mViewPager.setCurrentItem(currentItem); // 设置当前显示的item页面是0
	}

	/**
	 * PageAdapter
	 * @author yajun
	 *
	 */
	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * 返回true代表复用view对象 官方建议写法 return view == obj;
		 */
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		/**
		 * 销毁多余的item position就是将要被销毁的item的索引
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object); // 父类要求必须实现此方法.
			// 不实现会抛异常.
			Log.i(TAG, "destroyItem position:" + position
					+ ";position % imageViewList.size()" + position
					% imageViewList.size());
			// Log.i(TAG,
			// "container.equals(mViewPager):"+container.equals(mViewPager));
			// Log.i(TAG,
			// ";object.equals(imageViewList.get(position% imageViewList.size())):"+object.equals(imageViewList.get(position%
			// imageViewList.size())));
			mViewPager.removeView(imageViewList.get(position
					% imageViewList.size()));
		}

		/**
		 * 当需要加载item时出发此方法 position 就是将要被加载的item的索引
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int currentItem = mViewPager.getCurrentItem();
			Log.v(TAG, "currentItem:" + currentItem);
			Log.d(TAG, "instantiateItem position:" + position
					+ ";position % imageViewList.size()" + position
					% imageViewList.size());
			mViewPager.addView(imageViewList.get(position
					% imageViewList.size()));
			return imageViewList.get(position % imageViewList.size());
		}
	}

	/**
	 * PageChangeListener
	 * @author yajun
	 *
	 */
	class PointListener implements OnPageChangeListener {

		/**
		 * 当页面滚动状态改变时
		 */
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		/**
		 * 当页面滚动时
		 */
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		/**
		 * 当页面被选中时出发此方法
		 */
		@Override
		public void onPageSelected(int position) {
			int newPosition = position % imageViewList.size();

			// 切换到对应的描述信息, 和选中 点
			tvImageDescription.setText(imageDescriptionArrays[newPosition]);
			llPointGroup.getChildAt(previousPosition).setEnabled(true);
			llPointGroup.getChildAt(newPosition).setEnabled(false);

			previousPosition = newPosition;
		}

	}

	/**
	 * 设置自动滚动
	 */
	public void setAutoPlay(final Activity activity) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isStop) {
					System.out.println("切换下一个页面");
					SystemClock.sleep(2000);

					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							int newCurrentPosition = mViewPager
									.getCurrentItem() + 1;
							mViewPager.setCurrentItem(newCurrentPosition);
						}
					});
				}
			}
		}).start();
	}

}
