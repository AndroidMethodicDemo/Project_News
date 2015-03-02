package com.andong.pullrefresh.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class ACT_Base extends FragmentActivity{
	protected Activity mContext;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		// 初始化crash handler
		//CrashHandler.getInstance().init(this);
		
		mContext = ACT_Base.this;
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		initViews();
		
		bindEvents();
		
		initDatas();
	}

	/**
	 * 初始化view
	 */
	protected abstract void initViews();
	
	/**
	 * 绑定事件
	 */
	protected abstract void bindEvents();
	
	/**
	 * 加载数据，显示到界面
	 */
	protected abstract void initDatas();
}
