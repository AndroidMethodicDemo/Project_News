package com.csii.fragmentvp;


import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author panyi
 */
public class FragmentPre extends Fragment implements OnClickListener{
	
	/**
	 * 当前点击第几项
	 */
	private int currentIndex = 0;

	private TextView commonnotice, financialnotice, redeemnotice;
	private RelativeLayout line;
	private LinearLayout titleLayout;

	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;

	// 下划线宽度
	private int lineWidth;
	// 下划线当前位置
	private int linePosition;

	/*
	 * View
	 */
	private View rootView;
	
	/*
	 * 执行顺序先后
	 * onAttach 
	 * onCreate :保存恢复数据
	 * onCreateView
	 * onActivityCreated 具体操作
	 */
	public static FragmentPre getInstance(Bundle args) {
		FragmentPre creditFragment = new FragmentPre();
		if (args != null) {
			creditFragment.setArguments(args);
		}
		return creditFragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
//		callBack = (CallBack) activity;
		super.onAttach(activity);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_pre, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initLineView(rootView);
		initTitleView(rootView);
		initViewPager(rootView);
	}
	
	/**
	 * 初始化下划线
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void initLineView(View view) {
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		lineWidth = display.getWidth() / 3;
		line = (RelativeLayout) view.findViewById(R.id.line);// 底部横线
		android.view.ViewGroup.LayoutParams lp = line.getLayoutParams();
		lp.width = lineWidth;
		line.setLayoutParams(lp);
		linePosition = (int) line.getX();
	}
	
	/**
	 * 初始化选项卡菜单
	 */
	private void initTitleView(View view) {
		titleLayout = (LinearLayout) view.findViewById(R.id.titleLayout);//
		commonnotice = (TextView) view.findViewById(R.id.commonnotice);// 通用
		commonnotice.setTextColor(Color.WHITE);
		commonnotice.setOnClickListener(this);
		financialnotice = (TextView) view.findViewById(R.id.financialnotice);// 理财专项
		financialnotice.setOnClickListener(this);
		redeemnotice = (TextView) view.findViewById(R.id.redeemnotice);// 理财赎回
		redeemnotice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.commonnotice:
			currentIndex = 0;
			mPager.setCurrentItem(0);
			break;
		case R.id.financialnotice:
			currentIndex = 1;
			mPager.setCurrentItem(1);
			break;
		case R.id.redeemnotice:
			currentIndex = 2;
			mPager.setCurrentItem(2);
			break;
		}
		changTextColor(titleLayout, currentIndex);
	}

	/**
	 * 
	 * @param parent
	 *            父元素
	 * @param curent
	 *            当前选中
	 */
	private void changTextColor(LinearLayout parent, int curent) {
		for (int i = 0; i < parent.getChildCount(); i++) {
			TextView child = (TextView) parent.getChildAt(i);
			if (i == curent) {
				child.setTextColor(Color.WHITE);
			} else {
				child.setTextColor(getResources().getColor(
						R.color.menu_text_gray));
			}

		}
	}
	
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager(View view) {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(Fragment1.getInstance(null));
		fragmentsList.add(Fragment2.getInstance(null));
		fragmentsList.add(Fragment3.getInstance(null));
		
		mPager = (ViewPager) view.findViewById(R.id.vPager);
		mPager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(),fragmentsList));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> list;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> list) {
			super(fm);
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			currentIndex = arg0;
			changTextColor(titleLayout, currentIndex);
			Animation animation = null;
			int toX = 0;
			toX = arg0 * lineWidth;
			animation = new TranslateAnimation(linePosition, toX, 0, 0);
			linePosition = toX;
			animation.setFillAfter(true);
			animation.setDuration(300);
			line.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	}
}
