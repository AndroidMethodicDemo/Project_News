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
 */
public class Fragment1 extends Fragment {
	public static Fragment1 creditFragment;
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
	public static Fragment1 getInstance(Bundle args) {
		creditFragment = new Fragment1();
		if (args != null) {
			creditFragment.setArguments(args);
		}
		return creditFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment1, container, false);
		return rootView;
	}
}
