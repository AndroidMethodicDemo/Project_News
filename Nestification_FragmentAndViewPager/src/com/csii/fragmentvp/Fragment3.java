package com.csii.fragmentvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 
 * @author panyi
 * @category 按揭贷款
 *
 */
public class Fragment3 extends Fragment{

public static Fragment2 loanFragment;
	
	/*
	 * 业务流程处理
	 */
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			
			
		};
	};
	
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
	public static Fragment2 getInstance(Bundle args) {
		loanFragment = new Fragment2();
		if (args != null) {
			loanFragment.setArguments(args);
		}
		return loanFragment;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onAttach(Activity activity) {
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
		rootView = inflater.inflate(R.layout.fragment3, container, false);
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}
